package com.vacomall.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author gaojun.zhou
 * @since 2018-03-15
 */
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id",type=IdType.UUID)
    private String id;
    /**
     * 菜单名称
     */
    @TableField("menuName")
    private String menuName;
    /**
     * 父级菜单ID
     */
    private String pid;
    /**
     * 连接地址
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 深度
     */
    private Integer deep;
    /**
     * 资源名称
     */
    private String resource;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDeep() {
        return deep;
    }

    public void setDeep(Integer deep) {
        this.deep = deep;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", id=" + id +
        ", menuName=" + menuName +
        ", pid=" + pid +
        ", url=" + url +
        ", icon=" + icon +
        ", sort=" + sort +
        ", deep=" + deep +
        ", resource=" + resource +
        "}";
    }
}
