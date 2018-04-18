package com.vacomall.service;

import com.vacomall.entity.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
public interface UserService extends IService<User> {

	void insertUser(User user);

	void updateUser(User user);

}
