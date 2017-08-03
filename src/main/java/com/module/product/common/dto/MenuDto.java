package com.module.product.common.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象DTO,首页显示当前用户所属菜单使用.
 * Created by wangsongpeng on 2016/2/16.
 */
public class MenuDto {
    private Integer id;
    private String	menuName;		 /* 菜单名称 */
    private String	menuUrl;		 /* 菜单URL */
    private Boolean	menuEnable;    /* 菜单是否启用(1启用0不启用) */
    private String	menuIcon;		 /* 菜单的ICON图标 */
    private Boolean subIsExist;     /*是否有子菜单,true有就显示,false无不显示*/
    private String  menuCode; /*菜单代码*/
    private List<MenuDto> subMenu = new ArrayList<MenuDto>(); /*子菜单列表*/

    public MenuDto() {

    }

    public MenuDto(Integer id, String menuName, String menuUrl,String menuIcon,String menuCode) {
        this.id = id;
        this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.menuIcon = menuIcon;
        this.menuCode = menuCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Boolean getMenuEnable() {
        return menuEnable;
    }

    public void setMenuEnable(Boolean menuEnable) {
        this.menuEnable = menuEnable;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Boolean getSubIsExist() {
        return subIsExist;
    }

    public void setSubIsExist(Boolean subIsExist) {
        this.subIsExist = subIsExist;
    }

    public List<MenuDto> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<MenuDto> subMenu) {
        this.subMenu = subMenu;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
