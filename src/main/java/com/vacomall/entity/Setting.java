package com.vacomall.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 系统设置表
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@TableName("sys_setting")
public class Setting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id",type=IdType.UUID)
    private String Id;
    /**
     * KEY
     */
    @TableField("sysKey")
    private String sysKey;
    /**
     * 名称
     */
    @TableField("sysName")
    private String sysName;
    /**
     * 值
     */
    @TableField("sysValue")
    private String sysValue;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 说明
     */
    @TableField("sysDesc")
    private String sysDesc;


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getSysKey() {
        return sysKey;
    }

    public void setSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysValue() {
        return sysValue;
    }

    public void setSysValue(String sysValue) {
        this.sysValue = sysValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc;
    }

    @Override
    public String toString() {
        return "Setting{" +
        ", Id=" + Id +
        ", sysKey=" + sysKey +
        ", sysName=" + sysName +
        ", sysValue=" + sysValue +
        ", sort=" + sort +
        ", sysDesc=" + sysDesc +
        "}";
    }
}
