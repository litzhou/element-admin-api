package com.vacomall.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

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
    @TableId("id")
    private String id;
    /**
     * 角色名称
     */
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
    @TableField("createTime")
    private Date createTime;


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
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", roleDesc=" + roleDesc +
        ", roleState=" + roleState +
        ", createTime=" + createTime +
        "}";
    }
}
