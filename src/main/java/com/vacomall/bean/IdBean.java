package com.vacomall.bean;

import java.io.Serializable;

/**
 * 数组参数接受封装
 * @author Administrator
 *
 */
public class IdBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] ids;

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}
