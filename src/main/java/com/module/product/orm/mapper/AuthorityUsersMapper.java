package com.module.product.orm.mapper;

import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.dto.AuthorityUsersDto;
import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.dto.AuthorityUsersDto;
import com.module.product.orm.core.Mapper;
import com.module.product.orm.model.AuthorityUsers;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuthorityUsersMapper extends Mapper<AuthorityUsers> {

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     */
    List<AuthorityUsers> getUsersByEmail(String email);

    /**
     * 根据角色ID获取当前角色下的所有用户
     * @param roleId
     * @return
     */
    List<AuthorityUsers> getUsersByRoleId(Integer roleId);

    /**
     * 根据邮箱和角色ID模糊获取,不在当前角色下的用户
     * @param map
     * @return
     */
    List<AuthorityUsers> getUserByEmailAndRoleId(Map map);

    /**
     * 修改用户登录时间
     * @param users
     */
    void updateUserLoginDate(AuthorityUsers users);

    /**
     * 根据用户ID锁定或解锁该用户
     * @param users
     */
    void lockingUser(AuthorityUsers users);

    /**
     * 用户邮箱是否存在
     * @param users
     * @return
     */
    int checkUserEmailUnique(AuthorityUsers users);

    /**
     * 用户工号是否存在
     * @param users
     * @return
     */
    int checkUserNumUnique(AuthorityUsers users);

    /**
     * 用户手机号是否存在
     * @param users
     * @return
     */
    int checkUserMobileUnique(AuthorityUsers users);

    /**
     * 根据员工号 更新用户
     * @param users
     */
    void updateUserByNum(AuthorityUsers users);

    List<AuthorityUsers> select4DataTable(DataTablePageModel dataTablePageModel);

    List<AuthorityUsersDto> select4DataTableByChannel(DataTablePageModel dataTablePageModel);

    List<Map<Integer,Integer>> countProxyNum();

    List<AuthorityUsers> countExpandAmount();

    AuthorityUsers getEverydayStandDetail(@Param("loginUserId") Integer loginUserId, @Param("channelType")Integer channelType, @Param("startDate") Date startDate, @Param("endDate")Date endDate);
}