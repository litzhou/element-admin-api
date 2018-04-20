package com.vacomall.bean;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vacomall.entity.User;
import com.vacomall.service.MenuService;
import com.vacomall.service.UserRoleService;
import com.vacomall.service.UserService;

public class AuthRealm extends AuthorizingRealm {

	/**
	 * 用户服务
	 */
	@Autowired private UserService userService;
	/**
	 * 用户角色服务
	 */
	@Autowired private UserRoleService userRoleService;
	/**
	 * 角色菜单服务
	 */
	@Autowired private MenuService menuService;

    /**
     * 认证
     */
    @Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		UsernamePasswordToken user = (UsernamePasswordToken) token;
		User sysUser = userService.selectOne(new EntityWrapper<User>().eq("userName", user.getUsername()));
		
		if(sysUser == null){
			throw new UnknownAccountException();
		}
		if(sysUser.getUserState() != 1){
			throw new LockedAccountException();
		}
		//用户名，数据库中的密码,reaml名称
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),sysUser.getPassword(),getName());
		//盐值加密
		ByteSource byteSource = ByteSource.Util.bytes(user.getUsername());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser,sysUser.getPassword(),byteSource,getName());
		return info;
	}
	
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>(userRoleService.selectRoleIdentificationsByUid(user.getId()));
		Set<String> permissions = new HashSet<String>( menuService.selectResByUid(user.getId()));
		info.setRoles(roles);
		info.setStringPermissions(permissions);
		return info;
	}
	
	/**
	 * 密码加密 测试
	 * @param args
	 */
	public static void main(String[] args) {
		// MD5,"原密码","盐",加密次数
		String pwd =  new SimpleHash("MD5", "123", "test", 1024).toString();
		System.out.println(pwd);
		
	}
}
