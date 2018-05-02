package com.vacomall.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id",type=IdType.UUID)
    private String id;
    /**
     * 部门名称
     */
    @NotBlank(message="部门名称不能为空")
    @TableField("deptName")
    private String deptName;
    /**
     * 描述
     */
    @TableField("deptDesc")
    private String deptDesc;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    @Override
    public String toString() {
        return "Dept{" +
        ", id=" + id +
        ", deptName=" + deptName +
        ", deptDesc=" + deptDesc +
        "}";
    }
}
