package com.module.product.service.impl;


import com.alibaba.fastjson.JSON;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.bean.UserRealm;
import com.module.product.common.dto.OptDto;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityPermissionMapper;
import com.module.product.orm.mapper.AuthorityUserRoleMapper;
import com.module.product.orm.model.AuthorityPermission;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityPermissionService;
import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.bean.UserRealm;
import com.module.product.common.dto.OptDto;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.search.AuthorityPermissionSearchBean;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityPermissionMapper;
import com.module.product.orm.mapper.AuthorityUserRoleMapper;
import com.module.product.orm.model.AuthorityPermission;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityPermissionService;
import com.module.product.service.core.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Service Implementation:AuthorityPermission
 *
 * @author duia_builder
 * @date 2016-1-20
 */
@Transactional
@Service
public class AuthorityPermissionServiceImpl extends AbstractService<AuthorityPermission> implements AuthorityPermissionService {

    @Resource
    private AuthorityPermissionMapper permissionMapper;
    @Resource
    private AuthorityUserRoleMapper userRoleMapper;
    @Resource
    private UserRealm authRealm;
    @Resource
    private MenuCacheModel menuCacheModel;

    /**
     * 根据用户ID获取权限数据
     *
     * @param userId
     * @return
     */
    @Override
    public List<AuthorityPermission> findPermissionByUserID(Integer userId) {
        return this.permissionMapper.getPermissionByUserID(userId);
    }

    /**
     * 根据请求参数,进行角色授权
     *
     * @param search
     */
    @AdminLogAnn(operType = OperType.AUTH, operLevel = OperLevel.MAX, operDescribe = "对角色进行授权")
    @Override
    public void saveRoleAuth(AuthorityPermissionSearchBean search) {
        List<OptDto> optList = JSON.parseArray(search.getOptListStr(),OptDto.class);
        Map map = new HashMap();
        map.put("roleId", search.getRoleId());
        map.put("resourcesId", search.getMenuId());
        if (search.getDelMenuFlag()) {
            //删除当前角色对于该菜单的所有权限
            this.permissionMapper.delRoleAuth(map);
        } else {
            for (OptDto opt : optList) {
                map.put("operationId", opt.getId());
                if(!opt.getChecked()){
                    //删除当前角色对于菜单下该操作的所有权限
                    this.permissionMapper.delRoleAuth(map);
                }else{
                    AuthorityPermission p = new AuthorityPermission();
                    p.setRoleId(search.getRoleId());
                    p.setResourcesId(search.getMenuId());
                    p.setResourcesCode(search.getResCode());
                    p.setOperationId(opt.getId());
                    p.setOperationCode(opt.getCode());
                    p.setPermissionCode(p.getCode());
                    p.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
                    p.setLastModifyDatetime(new Date());
                    this.permissionMapper.insert(p);
                }
            }

        }
        //获取角色下的用户
        List<AuthorityUsers> us = userRoleMapper.getUsersByRoleId(search.getRoleId());
        if (!CollectionUtils.isEmpty(us)) {
            List<Integer> ids = new ArrayList<Integer>();
            for (AuthorityUsers u : us) {
                ids.add(u.getId());
            }
            this.authRealm.clearAllUserAuthorization();//删除全部用户的权限缓存
            this.menuCacheModel.clearMapMenuByUserId(ids);//删除当前用户集合的菜单缓存
        }
    }

}
