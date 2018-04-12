package com.vacomall.service.impl;

import com.vacomall.entity.User;
import com.vacomall.mapper.UserMapper;
import com.vacomall.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
