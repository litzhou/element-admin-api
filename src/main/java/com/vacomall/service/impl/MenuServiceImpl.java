package com.vacomall.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
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

	@Override
	public List<Map<String, Object>> selectMapMenus(String search,String pid) {
		// TODO Auto-generated method stub
		EntityWrapper<Menu> wrapper = new EntityWrapper<Menu>();
		wrapper.eq("pid", pid);
		wrapper.orderBy("sort",true);
		if (StringUtils.isNotBlank(search)) {
			wrapper.like("menuName", search);
		}
		List<Map<String,Object>> list = this.selectMaps(wrapper);
		if(!list.isEmpty()) {
			for(Map<String, Object> map : list) {
				/*if(MapUtils.getIntValue(map, "deep") < 3) {
				}*/
				map.put("children", this.selectMapMenus(search,MapUtils.getString(map, "id")));
			}
		}
		return list;
	}

}
