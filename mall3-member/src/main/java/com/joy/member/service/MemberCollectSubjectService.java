package com.joy.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.member.entity.MemberCollectSubjectEntity;

import java.util.Map;

/**
 * 会员收藏的专题活动
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:35:30
 */
public interface MemberCollectSubjectService extends IService<MemberCollectSubjectEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

