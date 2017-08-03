package com.module.product.service.impl;


import com.module.product.common.dto.RoleTreeDto;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.mapper.AuthorityRolesMapper;
import com.module.product.orm.model.AuthorityRoles;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityRolesService;
import com.module.product.service.core.AbstractService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Service Implementation:AuthorityRoles
 *
 * @author duia_builder
 * @date 2016-1-20
 */
@Transactional
@Service
public class AuthorityRolesServiceImpl extends AbstractService<AuthorityRoles> implements AuthorityRolesService {
    @Resource
    private AuthorityRolesMapper rolesMapper;

    /**
     * 查询所有角色
     */
    @Override
    public RoleTreeDto findAllRoles() {
        //角色默认的根级
        RoleTreeDto root = new RoleTreeDto(-1, "系统角色管理", true);
        root.setOpen(true);
        root.setChildren(this.findRoless());
        return root;
    }

    /**
     * 查询所有启用角色
     *
     * @return
     */
    @Override
    public RoleTreeDto findAllEnableRoles() {
        //角色默认的根级
        RoleTreeDto root = new RoleTreeDto(-1, "全部角色", true);
        root.setOpen(true);
        List<RoleTreeDto> rts = this.findRoless();
        ;
        if (!CollectionUtils.isEmpty(rts)) {
            for (RoleTreeDto r : rts) {
                if (!CollectionUtils.isEmpty(r.getChildren())) {
                    Iterator<RoleTreeDto> its = r.getChildren().iterator();
                    while (its.hasNext()) {
                        RoleTreeDto rt = its.next();
                        if (rt.getEnable() == 0) {
                            its.remove();
                        }
                    }
                }
            }
        }
        root.setChildren(rts);
        return root;
    }

    /**
     * 验证当前角色是否唯一
     *
     * @param roles
     * @return
     */
    @Override
    public boolean checkRoleUnique(AuthorityRoles roles) {
        return this.rolesMapper.checkRoleUnique(roles) == 0 ? true : false;
    }


    /**
     * 查询出所有父子角色
     *
     * @return
     */
    private List<RoleTreeDto> findRoless() {
        Integer userId = null;
        AuthorityUsers sessionUser = ShiroUtils.getSessionUser();
        if(sessionUser != null && sessionUser.getId() != 1){
            userId = sessionUser.getId();
        }
        List<AuthorityRoles> ParentRoles = this.rolesMapper.getParentRole();//获取所有父角色
        List<AuthorityRoles> SubRoles = this.rolesMapper.getSubRole(userId); //获取所有子菜单
        List<RoleTreeDto> roleTrees = new ArrayList<RoleTreeDto>();
        //如果不为空
        if (!CollectionUtils.isEmpty(ParentRoles)) {
            for (AuthorityRoles pr : ParentRoles) {
                RoleTreeDto roles = new RoleTreeDto(pr.getId(), pr.getName(), true);
                for (AuthorityRoles sr : SubRoles) {
                    if (sr.getParentId().intValue() == pr.getId().intValue()) {
                        roles.getChildren().add(new RoleTreeDto(sr.getId(), sr.getName(), sr.getEnable(), null, true, false));
                    }
                }
                roleTrees.add(roles);//添加到当前用户角色集合中
            }
        }
        return roleTrees;
    }
}
