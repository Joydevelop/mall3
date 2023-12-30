package com.joy.order.dao;

import com.joy.order.entity.MqMessageEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:38:08
 */
@Mapper
public interface MqMessageDao extends BaseMapper<MqMessageEntity> {
	
}
