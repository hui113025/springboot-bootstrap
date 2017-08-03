package com.module.product.common.bean;


import org.apache.commons.lang3.StringUtils;

/*
 * 权限对象
 * Created by wangsongpeng on 2016/2/17.
 */
public class Permission {

    private String menuCode;
    private String operCode;


    public Permission() {

    }

    public Permission(String menuCode, String operCode) {
        this.menuCode = menuCode;
        this.operCode = operCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }


    public String toCode() {
        if (StringUtils.isBlank(this.getMenuCode()) || StringUtils.isBlank(this.getOperCode())) {
            return null;
        }
        return this.getMenuCode() + ":" + this.getOperCode();
    }
}
