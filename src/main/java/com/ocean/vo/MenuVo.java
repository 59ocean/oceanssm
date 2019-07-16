package com.ocean.vo;

import java.util.List;

public class MenuVo {
    private String id;

    private boolean open =false;

    private boolean checked = false;

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }



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

    private List<MenuVo> children;

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

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

