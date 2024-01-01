package com.joy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joy.common.utils.PageUtils;
import com.joy.product.entity.AttrGroupEntity;
import com.joy.product.vo.AttrGroupRelationVo;

import java.util.Map;

/**
 * 属性分组
 *
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:48
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);
}

