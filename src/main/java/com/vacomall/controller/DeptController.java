package com.vacomall.controller;


import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.baomidou.mybatisplus.plugins.Page;
import com.vacomall.bean.IdBean;
import com.vacomall.bean.Rest;
import com.vacomall.entity.Dept;
import com.vacomall.service.DeptService;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

	@Autowired private DeptService deptService;
	
	/**
	 * 分页查询列表
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @return
	 */
	@GetMapping("/list")
	public Rest list(@RequestParam(value = "current", defaultValue = "1") int current,
			@RequestParam(value = "size", defaultValue = "10") int size, String search) {

		EntityWrapper<Dept> wrapper = new EntityWrapper<Dept>();
		if (StringUtils.isNotBlank(search)) {
			wrapper.like("deptName", search);
		}
		Page<Dept> page = deptService.selectPage(new Page<Dept>(current, size), wrapper);
		return Rest.okData(page);
	}
	/**
	 * 获取所有部门
	 * @param current
	 * @param size
	 * @param search
	 * @return
	 */
	@GetMapping("/Alllist")
	public Rest alllist() {
		return Rest.okData(deptService.selectList(null));
	}
	
	/**
	 * 新增
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public Rest add(@RequestBody Dept dept) {
		deptService.insert(dept);
		return Rest.ok();
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	public Rest delete(@RequestBody IdBean idBean) {
		if (idBean == null || ArrayUtils.isEmpty(idBean.getIds())) {
			return Rest.failure("客户端传入ID为空");
		}
		deptService.deleteBatchIds(Arrays.asList(idBean.getIds()));
		return Rest.ok("删除成功");
	}

	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@PutMapping("/edit")
	public Rest edit(@RequestBody Dept dept) {
		deptService.updateById(dept);
		return Rest.ok();
	}
	
}

