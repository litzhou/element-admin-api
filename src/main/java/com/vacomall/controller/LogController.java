package com.vacomall.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.vacomall.bean.Rest;
import com.vacomall.entity.Log;
import com.vacomall.service.LogService;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/log")
public class LogController {
	
	@Autowired private LogService logService;
	
	/**
	 * 分页查询列表
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @return
	 */
	@RequiresPermissions("log:list")
	@GetMapping("/list")
	public Rest list(@RequestParam(value = "current", defaultValue = "1") int current,
			@RequestParam(value = "size", defaultValue = "10") int size, String search) {

		EntityWrapper<Log> wrapper = new EntityWrapper<Log>();
		if (StringUtils.isNotBlank(search)) {
			wrapper.like("title", search).or().like("params", search).or().like("userName", search);
		}
		Page<Log> page = logService.selectPage(new Page<Log>(current, size), wrapper);
		return Rest.okData(page);
	}
	
}

