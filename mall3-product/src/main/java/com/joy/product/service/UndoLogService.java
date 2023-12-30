package com.joy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.product.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:48
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

