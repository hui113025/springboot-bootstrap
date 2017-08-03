package com.module.product.orm.mapper;


import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMenuMapper extends Mapper<AuthorityMenu> {

    /**
     * 获取父菜单
     * @param
     * @return
     */
    List<AuthorityMenu> getParentMenus();

    /**
     * 获取子菜单
     * @param
     * @return
     */
    List<AuthorityMenu> getSubMenus(@Param("list") List<Integer> list);

    /**
     * 验证菜单的唯一性
     * @param menu
     * @return
     */
    Integer checkMenuUnique(AuthorityMenu menu);

    List<Integer> getResourcesByUser(@Param("userId")Integer userId);
}