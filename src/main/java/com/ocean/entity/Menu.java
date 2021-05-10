package com.ocean.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.ocean.entity.BaseEntity;
import java.io.Serializable;

/**
 * 
 * @author chenhy
 * @since 2019-07-14
 */
@TableName("sys_menu")
public class Menu extends BaseEntity<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.UUID)
    private String id;
    /**
    * 菜单名
    */
    private String name;
    /**
    * 父菜单id
    */
    private String parentId;
    /**
    * 类型：1、菜单 2、功能
    */
    private Integer type;
    /**
    * 图标
    */
    private String icon;
    /**
    * 菜单排序
    */
    private Integer menuSort;
    /**
    * 菜单url
    */
    private String menuUrl;


    public String getId() {
    return id;
    }

    public void setId(String id) {
    this.id = id;
    }

    public String getName() {
    return name;
    }

    public void setName(String name) {
    this.name = name;
    }

    public String getParentId() {
    return parentId;
    }

    public void setParentId(String parentId) {
    this.parentId = parentId;
    }

    public Integer getType() {
    return type;
    }

    public void setType(Integer type) {
    this.type = type;
    }

    public String getIcon() {
    return icon;
    }

    public void setIcon(String icon) {
    this.icon = icon;
    }

    public Integer getMenuSort() {
    return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
    this.menuSort = menuSort;
    }

    public String getMenuUrl() {
    return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
    this.menuUrl = menuUrl;
    }
            }
