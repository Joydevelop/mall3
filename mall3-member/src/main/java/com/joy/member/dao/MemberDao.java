package com.joy.member.dao;

import com.joy.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Joy
 * @email joy020500@gmail.com
 * @date 2023-12-30 21:35:30
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
