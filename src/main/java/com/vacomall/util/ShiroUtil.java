package com.vacomall.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.vacomall.entity.User;

/**
 * Shiro工具类
 * @author Administrator
 *
 */
public class ShiroUtil {
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public static User getCurrUser() {
		Subject subject = SecurityUtils.getSubject();
		if(subject != null) {
			return (User) subject.getPrincipal();
		}
		return null;
	}
	
}
