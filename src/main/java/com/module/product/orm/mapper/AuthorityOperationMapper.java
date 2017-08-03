package com.module.product.orm.mapper;


import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityOperation;

import java.util.List;

public interface AuthorityOperationMapper extends Mapper<AuthorityOperation> {
    List<AuthorityOperation> getAuthOperListByMenuId(long menuId);/*根据菜单ID获取当前菜单下的操作*/
    Integer checkOperUnique(AuthorityOperation operation);/*验证菜单下操作码的唯一性*/
}