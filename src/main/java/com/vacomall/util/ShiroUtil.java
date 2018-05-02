package com.vacomall.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
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
	
	/**
	 * 密码盐值加密
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String md51024Pwd(String password,Object salt){
		return new SimpleHash("MD5", password, salt, 1024).toString();
	}
	
}
