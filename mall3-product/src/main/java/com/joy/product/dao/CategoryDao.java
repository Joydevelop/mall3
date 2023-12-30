package com.joy.product.dao;

import com.joy.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:49
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
