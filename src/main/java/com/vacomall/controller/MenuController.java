package com.vacomall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.bean.Rest;
import com.vacomall.entity.Menu;
import com.vacomall.service.MenuService;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

	@Autowired private MenuService menuService;
	
	/**
	 * 根据父级菜单ID获取子菜单列表
	 * @param pid
	 * @return
	 */
	@GetMapping("/getListByPid")
	public Rest getListByPid(@RequestParam(defaultValue="0") String pid) {
		
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.orderBy("sort",true);
		wrapper.eq("pid",pid);
		return Rest.okData(menuService.selectList(wrapper));
	}
}

