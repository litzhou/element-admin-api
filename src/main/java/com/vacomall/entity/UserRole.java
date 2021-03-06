package com.vacomall.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@TableName("sys_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id",type=IdType.UUID)
    private String Id;
    /**
     * 用户主键
     */
    @TableField("userId")
    private String userId;
    /**
     * 角色主键
     */
    @TableField("roleId")
    private String roleId;


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    @Override
    public String toString() {
        return "UserRole{" +
        ", Id=" + Id +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }

	public UserRole(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
