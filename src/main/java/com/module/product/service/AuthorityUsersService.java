package com.module.product.service;


import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.dto.LoginDto;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.core.Service;

import java.util.List;
import java.util.Map;

/**
 * Service Interface:AuthorityUsers
 *
 * @author duia_builder
 * @date 2016-1-20
 */
public interface AuthorityUsersService extends Service<AuthorityUsers> {

    LoginDto checkLogin(String email, String password, boolean isrem); /*根据用户名密码验证登录,并返回响应*/

    List<AuthorityUsers> findUsersByEmail(String email);/*根据邮箱获取用户*/

    List<AuthorityUsers> findUsersByRoleId(Integer roleId);/*根据角色ID获取当前角色下的所有用户*/

    List<AuthorityUsers> findUserByEmailAndRoleId(Map map);/*根据邮箱和角色ID,模糊获取不在当前角色下的用户*/

    void LockingUser(AuthorityUsers users);/*根据用户ID锁定或解锁该用户*/

    boolean checkUserEmailUnique(AuthorityUsers users);/*验证用户邮箱是否存在*/

    boolean checkUserNumUnique(AuthorityUsers users);/*验证用户工号是否存在*/

    boolean checkUserMobileUnique(AuthorityUsers users);/*验证用户手机号是否存在*/

    DataTablePageModel buildDataTablePageModelByChannelProxy(DataTablePageModel dataTablePageModel);

    DataTablePageModel buildDataTablePageModelByChannelExpand(DataTablePageModel dataTablePageModel);
}
