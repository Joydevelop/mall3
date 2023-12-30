package com.joy.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.ware.entity.PurchaseDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:39:36
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

