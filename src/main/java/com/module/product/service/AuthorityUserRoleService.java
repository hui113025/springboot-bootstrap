package com.module.product.service;


import com.module.product.orm.model.AuthorityUserRole;
import com.module.product.service.core.Service;
import com.module.product.orm.model.AuthorityUserRole;
import com.module.product.service.core.Service;

/**
 * Service Interface:AuthorityUserRole
 * @author duia_builder
 * @date 2016-1-20
 */
public interface AuthorityUserRoleService extends Service<AuthorityUserRole> {
    void deleteRoleUser(AuthorityUserRole ur);/*删除角色下的用户*/
    Boolean isAdminRole();
    Boolean isProxyRole();
    Boolean isExpandRole();
}