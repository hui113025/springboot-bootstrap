package com.module.product.web;


import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.dto.MenuTreeDto;
import com.module.product.common.dto.OperTreeDto;
import com.module.product.common.dto.RoleTreeDto;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.search.AuthorityPermissionSearchBean;
import com.module.product.service.AuthorityMenuService;
import com.module.product.service.AuthorityOperationService;
import com.module.product.service.AuthorityPermissionService;
import com.module.product.service.AuthorityRolesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 权限管理控制器
 * Created by wangsongpeng on 2016/2/25.
 */
@Controller
@RequestMapping("/per")
public class AuthorityPermissionController {
    @Resource
    private AuthorityRolesService authorityRolesService;
    @Resource
    private AuthorityMenuService authorityMenuService;
    @Resource
    private AuthorityOperationService authorityOperationService;
    @Resource
    private AuthorityPermissionService authorityPermissionService;

    /**
     * 跳转到角色管理页面
     * @return
     */
    @PermissionAnn(menuCode = "per",msg = "你没有进入角色授权页面的权限!")
    @RequestMapping(value = "/manager")
    public String forwardRoleManager(){
        return "authority/authority_per";
    }

    /**
     * 根据条件查询所有角色信息(Ztree).
     * @return
     */
    @PermissionAnn(menuCode = "per",operCode = "find",msg = "你没有角色授权页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/role/list",method = RequestMethod.POST)
    public RoleTreeDto serachRoleInfo(){
        return this.authorityRolesService.findAllEnableRoles();
    }
    /**
     * 根据条件查询所有操作信息(Ztree).
     * @return
     */
    @PermissionAnn(menuCode = "per",operCode = "find",msg = "你没有角色授权页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/oper/list",method = RequestMethod.POST)
    public OperTreeDto serachOperInfo(Integer roleId, Integer menuId){
        return this.authorityOperationService.findMenuOperByRoleID(menuId, roleId);
    }
    /**
     * 根据条件查询所有菜单信息(Ztree).
     * @return
     */
    @PermissionAnn(menuCode = "per",operCode = "find",msg = "你没有角色授权页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/menu/list",method = RequestMethod.POST)
    public MenuTreeDto serachMenuInfo(Integer roleId) {
        return this.authorityMenuService.findAllMenuByRoles(roleId);
    }
    /**
     * 对角色进行授权
     * @return
     */
    @AdminLogAnn(operType = OperType.AUTH,operLevel = OperLevel.MAX,operDescribe = "角色授权")
    @PermissionAnn(menuCode = "per",operCode = "roleAuth",msg = "你没有角色授权的权限!")
    @ResponseBody
    @RequestMapping(value = "/role/auth",method = RequestMethod.POST)
    public ResponseJsonModel roleAuth(AuthorityPermissionSearchBean serachBean){
        this.authorityPermissionService.saveRoleAuth(serachBean);
        return new ResponseJsonModel();
    }

}
