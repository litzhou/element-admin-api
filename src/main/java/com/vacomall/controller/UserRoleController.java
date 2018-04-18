package com.vacomall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.bean.Rest;
import com.vacomall.entity.UserRole;
import com.vacomall.service.UserRoleService;

/**
 * <p>
 * 用户角色关联表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {
	
	@Autowired private UserRoleService userRoleService;

	@GetMapping("/getRoleIdByUid")
	public Rest getRoleIdByUid(@RequestParam String userId) {
		EntityWrapper<UserRole> wrapper = new EntityWrapper<UserRole>();
		wrapper.setSqlSelect("roleId");
		wrapper.eq("userId", userId);
		return Rest.okData(userRoleService.selectObjs(wrapper));
	}
	
}

