package com.joy.product.dao;

import com.joy.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 品牌分类关联
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 20:39:48
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    @Update("update pms_category_brand_relation set catelog_name=#{name} where catelog_id=#{catId}")
    void updateCategory(@Param("catId") Long catId,@Param("name") String name);
}
