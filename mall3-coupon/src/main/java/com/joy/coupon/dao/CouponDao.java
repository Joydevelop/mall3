package com.joy.coupon.dao;

import com.joy.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:30:25
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
