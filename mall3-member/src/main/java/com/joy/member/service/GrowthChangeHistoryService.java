package com.joy.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.member.entity.GrowthChangeHistoryEntity;

import java.util.Map;

/**
 * 成长值变化历史记录
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:35:30
 */
public interface GrowthChangeHistoryService extends IService<GrowthChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

