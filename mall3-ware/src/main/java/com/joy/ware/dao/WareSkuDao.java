package com.joy.ware.dao;

import com.joy.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 商品库存
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:39:36
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {



    @Select("select sum(stock-stock_locked) from wms_ware_sku where sku_id=#{skuId}")
    Long getSkuHasStock(@Param("skuId")Long skuId);
}
