package com.vacomall.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import com.baomidou.mybatisplus.service.IService;
import com.vacomall.entity.Menu;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@CacheConfig(cacheNames = "menus")
public interface MenuService extends IService<Menu> {
	
	/**
	 * 查询用户权限
	 * @param id
	 * @return
	 */
	@Cacheable(key="#uid")
	List<String> selectResByUid(String uid);
	
	/**
	 * 查询菜单树
	 * @param search 条件
	 * @param pid 上级ID
	 * @return 
	 */
	List<Map<String, Object>> selectMapMenus(String search, String pid);

}
