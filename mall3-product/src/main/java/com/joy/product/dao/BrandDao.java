package com.joy.product.dao;

import com.joy.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:49
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
