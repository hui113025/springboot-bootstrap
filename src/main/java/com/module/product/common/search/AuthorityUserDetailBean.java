package com.module.product.common.search;


import com.module.product.orm.model.AuthorityUsers;

/**
 * 用户密码修改
 *
 * @author qiaolu
 * @date 2016/03/29
 */
public class AuthorityUserDetailBean extends AuthorityUsers {
    private String oldPassWord;//原密码
    private String newPassWord;//新密码

    public String getOldPassWord() {
        return this.oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWord() {
        return this.newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }
}