package com.module.product.service.impl;


import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityUserRoleMapper;
import com.module.product.common.constant.PermissionConstant;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityUserRoleMapper;
import com.module.product.orm.model.AuthorityUserRole;
import com.module.product.service.AuthorityUserRoleService;
import com.module.product.service.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service Implementation:AuthorityUserRole
 * @author duia_builder
 * @date 2016-1-20
 */
@Transactional(rollbackFor=Exception.class)
@Service
public class AuthorityUserRoleServiceImpl extends AbstractService<AuthorityUserRole> implements AuthorityUserRoleService {
    @Resource
    private AuthorityUserRoleMapper userRoleMapper;
    /**
     * 删除角色下的用户
     */
    @Override
    public void deleteRoleUser(AuthorityUserRole ur) {
        this.userRoleMapper.deleteRoleUser(ur);
    }

    @Override
    public Boolean isAdminRole() {
        Boolean yes = false;
        Condition condition = new Condition(AuthorityUserRole.class);
        condition.createCriteria().andEqualTo("userId", ShiroUtils.getSessionUser().getId());
        List<AuthorityUserRole> authorityUserRoles = userRoleMapper.selectByCondition(condition);
        for(AuthorityUserRole userRole : authorityUserRoles){
            Integer roleIdProxy = Integer.valueOf(PermissionConstant.ROLE_ID_PROXY);
            Integer roleIdExpand = Integer.valueOf(PermissionConstant.ROLE_ID_EXPAND);
            Boolean isChannel = userRole.getRoleId().equals(roleIdProxy) || userRole.getRoleId().equals(roleIdExpand);
            if(!isChannel){
                yes = true;
                break;
            }
        }
        return yes;
    }

    @Override
    public Boolean isProxyRole() {
        Boolean yes = false;
        Condition condition = new Condition(AuthorityUserRole.class);
        condition.createCriteria().andEqualTo("userId",ShiroUtils.getSessionUser().getId());
        List<AuthorityUserRole> authorityUserRoles = userRoleMapper.selectByCondition(condition);
        for(AuthorityUserRole userRole : authorityUserRoles){
            Integer roleIdProxy = Integer.valueOf(PermissionConstant.ROLE_ID_PROXY);
            if(userRole.getRoleId().equals(roleIdProxy)){
                yes = true;
                break;
            }
        }
        return yes;
    }

    @Override
    public Boolean isExpandRole() {
        Boolean yes = false;
        Condition condition = new Condition(AuthorityUserRole.class);
        condition.createCriteria().andEqualTo("userId",ShiroUtils.getSessionUser().getId());
        List<AuthorityUserRole> authorityUserRoles = userRoleMapper.selectByCondition(condition);
        for(AuthorityUserRole userRole : authorityUserRoles){
            Integer roleIdProxy = Integer.valueOf(PermissionConstant.ROLE_ID_EXPAND);
            if(userRole.getRoleId().equals(roleIdProxy)){
                yes = true;
                break;
            }
        }
        return yes;
    }
}
