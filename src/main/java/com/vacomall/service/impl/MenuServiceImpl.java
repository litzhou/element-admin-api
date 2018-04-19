package com.vacomall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vacomall.entity.Menu;
import com.vacomall.mapper.MenuMapper;
import com.vacomall.service.MenuService;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
	
	@Autowired private MenuMapper menuMapper;
	
	@Override
	public List<String> selectResByUid(String uid) {
		// TODO Auto-generated method stub
		return menuMapper.selectResByUid(uid);
	}

}
