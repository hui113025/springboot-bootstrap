package com.module.product.common.search;

/**
 * 角色授权请求参数实体
 * Created by wangsongpeng on 2016/1/18.
 */
public class AuthorityPermissionSearchBean {

    private Integer roleId;/*角色ID*/
    private Integer menuId;/*菜单ID*/
    private String resCode;/*菜单code*/
    private boolean delMenuFlag;/*是否删除角色下的菜单权限 true:删除false:不删除*/
    private boolean delOperFlag;/*是否删除角色下的菜单下的操作权限 true:删除false:不删除*/
    private String  optListStr;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }


    public boolean getDelMenuFlag() {
        return delMenuFlag;
    }

    public void setDelMenuFlag(boolean delMenuFlag) {
        this.delMenuFlag = delMenuFlag;
    }

    public boolean getDelOperFlag() {
        return delOperFlag;
    }

    public void setDelOperFlag(boolean delOperFlag) {
        this.delOperFlag = delOperFlag;
    }

    public String getOptListStr() {
        return optListStr;
    }

    public void setOptListStr(String optListStr) {
        this.optListStr = optListStr;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
}
