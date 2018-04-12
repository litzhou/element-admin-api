package com.vacomall.service.impl;

import com.vacomall.entity.Role;
import com.vacomall.mapper.RoleMapper;
import com.vacomall.service.RoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
