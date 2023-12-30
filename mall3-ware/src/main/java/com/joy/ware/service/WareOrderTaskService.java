package com.joy.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.ware.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:39:36
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

