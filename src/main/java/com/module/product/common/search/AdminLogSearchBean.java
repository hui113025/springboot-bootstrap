package com.module.product.common.search;


import com.module.product.common.bean.DataTablePageModel;

public class AdminLogSearchBean extends DataTablePageModel {
    private String userName;
    private String operType;
    private Integer operLevel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public Integer getOperLevel() {
        return operLevel;
    }

    public void setOperLevel(Integer operLevel) {
        this.operLevel = operLevel;
    }

}
