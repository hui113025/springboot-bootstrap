package com.module.product.common.bean;

import com.module.product.service.AuthorityPermissionService;
import com.module.product.service.AuthorityUsersService;
import com.module.product.orm.model.AuthorityPermission;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityPermissionService;
import com.module.product.service.AuthorityUsersService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private AuthorityPermissionService permissionService;
    @Resource
    private AuthorityUsersService authorityUsersService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//创建Shiro权限数据对象
        Integer userId = Integer.valueOf(principalCollection.toString());//获取当前用户ID
        AuthorityUsers users = this.authorityUsersService.findById(userId);
        if (users != null) {
            if (users.getAdministrator() == 1) {
                //如果是超级管理员,赋予所有权限
                authorizationInfo.addStringPermission("*");
            } else {
                List<AuthorityPermission> permissionList = this.permissionService.findPermissionByUserID(users.getId());
                if (!CollectionUtils.isEmpty(permissionList)) {
                    for (AuthorityPermission p : permissionList) {
                        String permissionCode = p.getPermissionCode();
                        authorizationInfo.addStringPermission(permissionCode);
                    }
                }
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(usernamePasswordToken.getPrincipal(),
                usernamePasswordToken.getCredentials(), usernamePasswordToken.getUsername());
        return info;
    }

    public void clearAllUserAuthorization() {
        this.getAuthorizationCache().clear();
    }

}
