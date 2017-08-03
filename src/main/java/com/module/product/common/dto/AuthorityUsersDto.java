package com.module.product.common.dto;


import com.module.product.orm.model.AuthorityUsers;

/**
 * Created by zhenghui on 2017/6/2.
 */
public class AuthorityUsersDto extends AuthorityUsers {
    private String emailExpand;
    private Integer proxyNum;
    private Integer studentNum;

    public String getEmailExpand() {
        return emailExpand;
    }

    public void setEmailExpand(String emailExpand) {
        this.emailExpand = emailExpand;
    }

    public Integer getProxyNum() {
        return proxyNum;
    }

    public void setProxyNum(Integer proxyNum) {
        this.proxyNum = proxyNum;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }
}
