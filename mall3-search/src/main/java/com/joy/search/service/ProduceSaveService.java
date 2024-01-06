package com.joy.search.service;

import com.joy.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author Joy
 */
public interface ProduceSaveService {
    void productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
