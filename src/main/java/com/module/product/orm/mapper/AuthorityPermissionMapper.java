package com.module.product.orm.mapper;


import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityPermission;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AuthorityPermissionMapper extends Mapper<AuthorityPermission> {
    List<AuthorityPermission> getPermissionByUserID(long userId);/*根据用户ID获取用户的权限数据*/

    List<Integer> getMenuIdByRoleId(Map map); /*根据角色ID获取当前角色的菜单ID*/

    List<Integer> getOperIdByRoleMenu(Map map);/*根据角色与菜单ID获取当前操作的ID*/

    List<String> getCateIdByRoleMenuOper(Map map);/*根据角色,菜单,操作ID获取当前CATE分类的ID*/

    void delRoleAuth(Map map);/*根据参数删除角色权限*/

    Set<String> findCateIdByAdmin();
}