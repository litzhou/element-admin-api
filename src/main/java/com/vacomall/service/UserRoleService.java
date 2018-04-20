package com.vacomall.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.UserRole;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
public interface UserRoleService extends IService<UserRole> {
	
	/**
	 * 获取用户角色标识集合，一般不用
	 * @param uid
	 * @return
	 */
	List<String> selectRoleIdentificationsByUid(String uid);

}
