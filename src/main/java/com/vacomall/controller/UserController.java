package com.vacomall.controller;

import java.util.Arrays;
import java.util.Date;

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
import com.vacomall.entity.User;
import com.vacomall.service.UserService;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 查询用户列表
	 * 
	 * @param page
	 * @param size
	 * @param search
	 * @return
	 */
	@GetMapping("/list")
	public Rest list(@RequestParam(value = "current", defaultValue = "1") int current,
			@RequestParam(value = "size", defaultValue = "10") int size, String search) {

		EntityWrapper<User> wrapper = new EntityWrapper<User>();
		if (StringUtils.isNotBlank(search)) {
			wrapper.like("userName", search);
		}
		Page<User> page = userService.selectPage(new Page<User>(current, size), wrapper);
		return Rest.okData(page);
	}
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@PostMapping("/add")
	public Rest add(@RequestBody User user) {
		user.setCreateTime(new Date());
		userService.insertUser(user);
		return Rest.ok();
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete")
	public Rest delete(@RequestBody IdBean idBean) {
		if (idBean == null || ArrayUtils.isEmpty(idBean.getIds())) {
			return Rest.failure("客户端传入ID为空");
		}
		userService.deleteBatchIds(Arrays.asList(idBean.getIds()));
		return Rest.ok("删除成功");
	}

	/**
	 * 修改
	 * @param user
	 * @return
	 */
	@PutMapping("/edit")
	public Rest edit(@RequestBody User user) {
		userService.updateUser(user);
		return Rest.ok();
	}

	
}
