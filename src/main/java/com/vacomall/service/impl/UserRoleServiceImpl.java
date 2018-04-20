package com.vacomall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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
	
	@Override
	public List<String> selectRoleIdentificationsByUid(String uid) {
		// TODO Auto-generated method stub
		List<Object> list = super.selectObjs(new EntityWrapper<UserRole>().setSqlSelect("roleId").eq("userId", uid));
		List<String> strList = new ArrayList<String>();
		list.forEach(t -> {strList.add((String)t);});
		return strList;
	}

}
