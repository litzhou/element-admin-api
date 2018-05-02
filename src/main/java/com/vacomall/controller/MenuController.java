package com.vacomall.controller;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.bean.IdBean;
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
	
	/**
	 * 查询所有的菜单数据
	 * @param search
	 * @return
	 */
	@RequiresPermissions("menu:list")
	@GetMapping("/list/all")
	public Rest listAll( String search) {

		List<Map<String, Object>> list = menuService.selectMapMenus(search,"0");
		return Rest.okData(list);
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequiresPermissions("menu:add")
	@PostMapping("/add")
	public Rest add(@RequestBody @Valid Menu menu) {
		menuService.insert(menu);
		return Rest.ok();
	}
	
	/**
	 * 编辑
	 * @param menu
	 * @return
	 */
	@PutMapping("/edit")
	public Rest edit(@RequestBody @Valid Menu menu) {
		menuService.updateById(menu);
		return Rest.ok();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequiresPermissions("menu:delete")
	@DeleteMapping("/delete")
	public Rest delete(@RequestBody IdBean idBean) {
		if (idBean == null || ArrayUtils.isEmpty(idBean.getIds())) {
			return Rest.failure("客户端传入ID为空");
		}
		menuService.deleteBatchIds(Arrays.asList(idBean.getIds()));
		return Rest.ok("删除成功");
	}
	
}

