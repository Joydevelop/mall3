package com.joy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:48
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

