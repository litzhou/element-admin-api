package com.vacomall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.UserRole;
import com.vacomall.mapper.UserRoleMapper;
import com.vacomall.service.UserRoleService;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
	
	@Autowired private UserRoleMapper userRoleMapper;
	
	@Override
	public List<String> selectRoleIdentificationsByUid(String uid) {
		// TODO Auto-generated method stub
		return  userRoleMapper.selectRoleIdentificationsByUid(uid);
	}

}
