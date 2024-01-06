package com.joy.search.service.impl;

import com.alibaba.fastjson2.JSON;
import com.joy.common.to.es.SkuEsModel;
import com.joy.search.config.SearchConfig;
import com.joy.search.constant.EsConstant;
import com.joy.search.service.ProduceSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Joy
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProduceSaveServiceImpl implements ProduceSaveService {
    private final RestHighLevelClient restHighLevelClient;


    @Override
    public void productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
        //保存es
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel skuEsModel : skuEsModels) {
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsModel.getSkuId().toString());
            String jsonString = JSON.toJSONString(skuEsModel);
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, SearchConfig.COMMON_OPTIONS);
        //是否失败
        boolean isSuccess = bulk.hasFailures();
        log.info("商品上架完成：{}", isSuccess);
    }
}
