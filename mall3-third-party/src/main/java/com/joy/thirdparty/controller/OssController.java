package com.joy.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.joy.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Joy
 */
@RestController
public class OssController {

    @Value("${spring.cloud.alicloud.oss.endpoint}")
    private String endPoint;
    @Value("${spring.cloud.alicloud.oss.bucket}")
    private String bucket;
    @Value("${spring.cloud.alicloud.access-key}")
    private String accessId;
    @Value("${spring.cloud.alicloud.secret-key}")
    private String secretKey;


    @RequestMapping("/oss/policy")
    public R policy() {
        String host="https://" + bucket + "." + endPoint;
        String format=new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String dir = format + "/";
        long expertTime=30;
        long expireEndTime=System.currentTimeMillis() + expertTime * 1000;
        //授权
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessId, secretKey);
        OSS ossClient = new OSSClientBuilder().build(endPoint, credentialsProvider);
        //过期时间
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE,0,1048576000);
        policyConditions.addConditionItem(MatchMode.StartWith,PolicyConditions.COND_KEY,dir);


        String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
        byte[] bytes = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodingPolicy = BinaryUtil.toBase64String(bytes);
        String postSignature = ossClient.calculatePostSignature(postPolicy);
        Map<String, String> resMap = new LinkedHashMap<>();
        resMap.put("accessId",accessId);
        resMap.put("policy",encodingPolicy);
        resMap.put("signature",postSignature);
        resMap.put("dir",dir);
        resMap.put("host",host);
        resMap.put("expire",String.valueOf(expireEndTime / 1000));
        return R.ok().put("data",resMap);
    }
}
