package com.vacomall.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.User;
import com.vacomall.entity.UserRole;
import com.vacomall.mapper.UserMapper;
import com.vacomall.service.UserRoleService;
import com.vacomall.service.UserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Autowired private UserRoleService userRoleService;
	
	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		this.insert(user);
		if(ArrayUtils.isNotEmpty(user.getRoleIds())) {
			for(String roleId : user.getRoleIds()) {
				userRoleService.insert(new UserRole(user.getId(), roleId));
			}
		}
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.updateById(user);
		if(ArrayUtils.isNotEmpty(user.getRoleIds())) {
			userRoleService.delete(new EntityWrapper<UserRole>().eq("userId", user.getId()));
			for(String roleId : user.getRoleIds()) {
				userRoleService.insert(new UserRole(user.getId(), roleId));
			}
		}
	}

}
