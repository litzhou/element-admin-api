package com.vacomall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.bean.LoginBean;
import com.vacomall.bean.Rest;
import com.vacomall.entity.User;
import com.vacomall.service.MenuService;
import com.vacomall.service.UserService;

/**
 * 登录控制器
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired private UserService userService;
	@Autowired private MenuService menuService;
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/doLogin")
	public Rest doLogin(@RequestBody LoginBean loginBean,HttpSession session) {
		
		if(loginBean == null || StringUtils.isAnyBlank(loginBean.getUsername(),loginBean.getPassword())) {
			return Rest.failure("用户名或密码不能为空");
		}
		
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();
		
		User user = userService.selectOne(new EntityWrapper<User>().eq("userName", username).eq("password",password));
		if(user == null) {
			return  Rest.failure("用户名或密码错误");
		}
		user.setPassword("null");
		session.setAttribute("user", user);
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("menu", menuService.selectResByUid(user.getId()));
		return Rest.okData(map);
		
	}
	
	/**
	 * 获取登录用户信息
	 * @param user
	 * @return
	 */
	@GetMapping("/info")
	public Rest info(@SessionAttribute(required=false) User user) {
		if(user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("menu", menuService.selectResByUid(user.getId()));
			//map.put("menu", new String[]{"index","icon","dep","lang","echarts","editor","markdown","crud"});
			return Rest.okData(map);
		}
		return Rest.failure("用户登录已过期");
	}

	/**
	 * 退出
	 * @return
	 */
	@GetMapping("/logout")
	public Rest logout(HttpSession session) {
		session.invalidate();
		return Rest.ok("用户已退出");
	}
	
	/**
	 * 获取登录用户的权限,返回权限列表
	 * @return
	 */
	@GetMapping("/auth")
	public Rest auth(@SessionAttribute(required=false) User user) {
		
		if(user == null) {
			return Rest.failure("用户登录已过期");
		}
		List<String> listRes = menuService.selectResByUid(user.getId());
		return Rest.okData(listRes);
	}
	
}
