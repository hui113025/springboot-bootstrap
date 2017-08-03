package com.module.product.service;


import com.module.product.common.dto.OperTreeDto;
import com.module.product.service.core.Service;
import com.module.product.common.dto.OperTreeDto;
import com.module.product.orm.model.AuthorityOperation;
import com.module.product.service.core.Service;

public interface AuthorityOperationService extends Service<AuthorityOperation> {
    OperTreeDto findMenuOperByRoleID(Integer menuId, Integer roleId);/*查询菜单下的所有操作树,并根据角色权限,对操作设置勾选状态*/

    boolean checkOperUnique(AuthorityOperation operation);/*验证菜单下操作码的唯一性*/
}