package com.vacomall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.vacomall.entity.Menu;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
public interface MenuMapper extends BaseMapper<Menu> {

	List<String> selectResByUid(@Param("uid") String uid);
	
}
