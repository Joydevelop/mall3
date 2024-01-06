package com.joy.product.feign;

import com.joy.common.utils.R;
import com.joy.product.vo.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Joy
 */
@FeignClient("mall3-ware")
public interface WareFeignService {
    @PostMapping("/ware/waresku/hasStock")
     R<List<SkuHasStockVo>> getSkuHasStock(@RequestBody List<Long> skuIds);
}
