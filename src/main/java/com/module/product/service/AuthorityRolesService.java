package com.module.product.service;


import com.module.product.common.dto.RoleTreeDto;
import com.module.product.orm.model.AuthorityRoles;
import com.module.product.service.core.Service;

/**
 * Service Interface:AuthorityRoles
 *
 * @author duia_builder
 * @date 2016-1-20
 */
public interface AuthorityRolesService extends Service<AuthorityRoles> {
    RoleTreeDto findAllRoles();//查询所有角色

    RoleTreeDto findAllEnableRoles();//查询所有启用角色

    boolean checkRoleUnique(AuthorityRoles roles); /*验证当前角色是否唯一*/
}