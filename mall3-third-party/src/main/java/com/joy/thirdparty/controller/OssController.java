package com.joy.thirdparty.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.exceptions.ClientException;
import com.joy.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
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

    @Value("${mall3.oss.endpoint}")
    private String endPoint;
    @Value("${mall3.oss.bucket}")
    private String bucket;

    @RequestMapping("/oss/policy")
    public R policy() throws ClientException {
        String host="https://" + bucket + "." + endPoint;
        String format=new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        String dir = format + "/";
        //授权
        DefaultCredentialProvider credentialsProvider = CredentialsProviderFactory.newDefaultCredentialProvider("LTAI5tDZxmaFVCRdB819DoXo","8qNwkYJAnzSJEddha60jxTzBB0IpgC");
        OSS ossClient = new OSSClientBuilder().build(endPoint, credentialsProvider);
        //过期时间
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String accessId = credentialsProvider.getCredentials().getAccessKeyId();
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> resMap = new LinkedHashMap<>();
        resMap.put("accessId",accessId);
        resMap.put("policy",encodedPolicy);
        resMap.put("signature",postSignature);
        resMap.put("dir",dir);
        resMap.put("host",host);
        resMap.put("expire",String.valueOf(expireEndTime / 1000));
        return R.ok().put("data",resMap);
    }
}
