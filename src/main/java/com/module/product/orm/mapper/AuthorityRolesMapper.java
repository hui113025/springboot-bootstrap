package com.module.product.orm.mapper;


import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityRoles;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AuthorityRolesMapper extends Mapper<AuthorityRoles> {
    List<AuthorityRoles> getParentRole();//获取所有父角色

    List<AuthorityRoles> getSubRole(@Param("userId")Integer userId);//获取所有子角色

    void updateRoleSwitch(Map map);//修改角色的开关状态:启用或者停用

    Integer checkRoleUnique(AuthorityRoles roles); /*验证当前角色是否唯一*/
}