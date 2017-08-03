package com.module.product.orm.mapper;


import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityUserRole;
import com.module.product.orm.model.AuthorityUsers;

import java.util.List;

public interface AuthorityUserRoleMapper extends Mapper<AuthorityUserRole> {
    List<AuthorityUsers> getUsersByRoleId(Integer roleId);/*获取当前角色下的所有用户*/

    void  deleteRoleUser(AuthorityUserRole ur);     /*删除角色下的用户*/
}