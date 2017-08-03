package com.module.product.service;


import com.module.product.service.core.Service;
import com.module.product.common.search.AuthorityPermissionSearchBean;
import com.module.product.orm.model.AuthorityPermission;
import com.module.product.service.core.Service;

import java.util.List;

public interface AuthorityPermissionService extends Service<AuthorityPermission> {

  List<AuthorityPermission> findPermissionByUserID(Integer userId);/*根据用户ID获取权限数据*/

  void saveRoleAuth(AuthorityPermissionSearchBean serachBean);/*根据请求参数,进行角色授权*/

}