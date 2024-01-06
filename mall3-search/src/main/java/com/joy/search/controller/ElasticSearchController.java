package com.joy.search.controller;

import com.joy.common.to.es.SkuEsModel;
import com.joy.common.utils.R;
import com.joy.search.service.ProduceSaveService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Joy
 */
@RestController
@RequestMapping("/search/save")
public class ElasticSearchController {
    @Resource
    private ProduceSaveService produceSaveService;

    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels) {
        try {
            produceSaveService.productStatusUp(skuEsModels);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.ok();
    }


}
