package com.vacomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.UserRole;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

	List<String> selectRoleIdentificationsByUid(@Param("uid") String uid);

}
