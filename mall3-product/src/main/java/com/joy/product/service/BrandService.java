package com.joy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:49
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

