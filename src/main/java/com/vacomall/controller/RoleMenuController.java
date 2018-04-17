package com.vacomall.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.bean.Rest;
import com.vacomall.entity.RoleMenu;
import com.vacomall.service.RoleMenuService;

/**
 * <p>
 * 角色菜单关联表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
	
	@Autowired private RoleMenuService roleMenuService;

	/**
	 * 根据角色ID获取角色权限，得到的结果角色用有的菜单ID集合
	 * @param roleId
	 * @return
	 */
	@GetMapping("/getMenuIdByRoleId")
	public Rest getMenuIdByRoleId(@RequestParam String roleId) {
		
		EntityWrapper<RoleMenu> wrapper = new EntityWrapper<RoleMenu>();
		wrapper.setSqlSelect("menuId");
		wrapper.eq("roleId", roleId);
		return Rest.okData(roleMenuService.selectObjs(wrapper));
	}
	
	/**
	 * 分配权限
	 * @param roleId
	 * @param authIds
	 * @return
	 */
	@PostMapping("/saveAuth")
	public Rest saveAuth(String roleId,@RequestParam("authIds[]") String[] authIds) {
		if(StringUtils.isNotBlank(roleId)){
			roleMenuService.delete(new EntityWrapper<RoleMenu>().eq("roleId", roleId));
			if(ArrayUtils.isNotEmpty(authIds)) {
				List<RoleMenu> list = new ArrayList<RoleMenu>();
				for(String authId : authIds) {
					list.add(new RoleMenu(roleId,authId));
				}
				roleMenuService.insertBatch(list);
			}
			
			return Rest.ok("权限分配成功");
		}
		return Rest.failure("分配权限异常,请稍后重试");
	}
}

