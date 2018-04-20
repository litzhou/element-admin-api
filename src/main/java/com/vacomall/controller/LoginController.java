package com.vacomall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vacomall.bean.LoginBean;
import com.vacomall.bean.Rest;
import com.vacomall.entity.User;
import com.vacomall.service.MenuService;
import com.vacomall.util.ShiroUtil;

/**
 * 登录控制器
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private MenuService menuService;

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@PostMapping("/doLogin")
	public Rest doLogin(@RequestBody LoginBean loginBean, HttpSession session) {

		if (loginBean == null || StringUtils.isAnyBlank(loginBean.getUsername(), loginBean.getPassword())) {
			return Rest.failure("用户名或密码不能为空");
		}
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();

		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		if (!currentUser.isAuthenticated()) {
			try {
				currentUser.login(token);
			} catch (UnknownAccountException uae) {
				return Rest.failure("未知用户");
			} catch (IncorrectCredentialsException ice) {
				return Rest.failure("密码错误");
			} catch (LockedAccountException lae) {
				return Rest.failure("账号已锁定");
			} catch (AuthenticationException ae) {
				return Rest.failure("服务器繁忙,稍后重试");
			}
		}

		User user = (User) currentUser.getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("menu", menuService.selectResByUid(user.getId()));
		return Rest.okData(map);

	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/info")
	public Rest info() {
		User user = ShiroUtil.getCurrUser();
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("menu", menuService.selectResByUid(user.getId()));
			return Rest.okData(map);
		}
		return Rest.failure("用户登录已过期");
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	@GetMapping("/logout")
	public Rest logout() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
		}
		return Rest.ok("已退出");
	}

	/**
	 * 获取登录用户的权限,返回权限列表
	 * 
	 * @return
	 */
	@GetMapping("/auth")
	public Rest auth() {
		User user = ShiroUtil.getCurrUser();
		if (user != null) {
			List<String> listRes = menuService.selectResByUid(user.getId());
			return Rest.okData(listRes);
		}
		return Rest.failure("用户登录已过期");

	}

}
