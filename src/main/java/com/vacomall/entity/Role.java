package com.vacomall.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@TableName("sys_role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	@TableField("roleName")
	private String roleName;
	/**
	 * 角色描述
	 */
	@TableField("roleDesc")
	private String roleDesc;
	/**
	 * 状态,1-启用,-1禁用
	 */
	@TableField("roleState")
	private Integer roleState;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@TableField("createTime")
	private Date createTime;

	/**
	 * 角色标识
	 */
	@TableField("roleIdentification")
	private String roleIdentification;

	public String getRoleIdentification() {
		return roleIdentification;
	}

	public void setRoleIdentification(String roleIdentification) {
		this.roleIdentification = roleIdentification;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Integer getRoleState() {
		return roleState;
	}

	public void setRoleState(Integer roleState) {
		this.roleState = roleState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleState=" + roleState
				+ ", createTime=" + createTime + ", roleIdentification=" + roleIdentification + "]";
	}

}
