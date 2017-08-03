package com.module.product.service;


import com.module.product.common.dto.MenuDto;
import com.module.product.common.dto.MenuTreeDto;
import com.module.product.orm.model.AuthorityMenu;
import com.module.product.service.core.Service;

import java.util.List;

public interface AuthorityMenuService extends Service<AuthorityMenu> {
    List<MenuDto> findUserAuthMenu();  /*查询出用户的权限菜单*/

    MenuTreeDto findAllAuthMenu();     /*查询出所有菜单*/

    MenuTreeDto findAllMenuByRoles(Integer roleId);  /*查询出所有菜单,并对当前角色下的菜单设置勾选状态*/

    AuthorityMenu findAuthMenuById(Integer id); /*根据菜单Id查询当前菜单实体*/

    boolean checkMenuUnique(AuthorityMenu menu);/*验证菜单的唯一性*/

    List<AuthorityMenu> findAllChildrenMenu();
}