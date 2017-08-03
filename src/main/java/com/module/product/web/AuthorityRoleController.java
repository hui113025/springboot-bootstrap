package com.module.product.web;


import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.bean.UserRealm;
import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.dto.RoleTreeDto;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.model.AuthorityRoles;
import com.module.product.orm.model.AuthorityUserRole;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityRolesService;
import com.module.product.service.AuthorityUserRoleService;
import com.module.product.service.AuthorityUsersService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理控制器
 * Created by wangsongpeng on 2016/2/25.
 */
@Controller
@RequestMapping("/role")
public class AuthorityRoleController {
    @Resource
    private AuthorityRolesService authorityRolesService;
    @Resource
    private AuthorityUsersService authorityUsersService;
    @Resource
    private AuthorityUserRoleService authorityUserRoleService;
    @Resource
    private UserRealm authRealm;
    @Resource
    private MenuCacheModel menuCacheModel;


    /**
     * 跳转到角色管理页面
     * @return
     */
    @PermissionAnn(menuCode = "role",msg = "你没有进入员工授权页面的权限!")
    @RequestMapping(value = "/manager")
    public String forwardRoleManager(Model model){
        return "authority/authority_role";
    }

    /**
     * 根据条件查询所有角色信息,返回树形.
     * @return
     */
    @PermissionAnn(menuCode = "role",operCode = "findRole",msg = "你没有员工授权页面查询角色的权限!")
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public RoleTreeDto serachRoleInfo(){
        return this.authorityRolesService.findAllRoles();
    }


    /**
     * 根据条件新增或修改角色
     * @param roles:新增或修改的角色
     * @return
     */
    @AdminLogAnn(operType = OperType.AUTH,operLevel = OperLevel.NORM,operDescribe = "添加或修改角色")
    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public ResponseJsonModel switchRole(AuthorityRoles roles){
        ResponseJsonModel arm = new ResponseJsonModel();
        roles.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
        roles.setLastModifyDatetime(new Date());
        if(roles.getId() == -1){
           //新增
           roles.setId(null);
           this.authorityRolesService.save(roles);
        }else{
          //修改
           this.authorityRolesService.update(roles);
           this.authRealm.clearAllUserAuthorization();//删除全部用户的权限缓存
           this.menuCacheModel.clearMapMenu();//删除全部用户的菜单缓存
        }
        arm.setResult(roles.getId());
        return arm;
    }

    /**
     * 获取角色下的所有员工
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value = "/list/user",method = RequestMethod.POST)
    public String listUserByRole(Model model, Integer roleId){
        List<AuthorityUsers> us = this.authorityUsersService.findUsersByRoleId(roleId);
        model.addAttribute("us", us);
        model.addAttribute("rid", roleId);
        return "authority/role_user_list";
    }

    /**
     * 根据邮箱和角色ID,模糊获取不在当前角色下的用户
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/email",method = RequestMethod.POST)
    public List<AuthorityUsers> listUserByEmail(HttpServletRequest request){
        Integer roleId =Integer.valueOf(request.getParameter("roleId"));
        String email = request.getParameter("email");
        Map map = new HashMap();
        map.put("email", email);
        map.put("roleId", roleId);
        return this.authorityUsersService.findUserByEmailAndRoleId(map);
    }


    /**
     * 用户授与角色权限
     * @param roleId 角色ID
     * @param email  用户电子邮箱
     * @return
     */
    @AdminLogAnn(operType = OperType.AUTH,operLevel = OperLevel.MAX,operDescribe = "对员工进行角色授权")
    @PermissionAnn(menuCode = "role",operCode = "userAuth",msg = "你没有员工授权的权限!")
    @ResponseBody
    @RequestMapping(value = "/user/auth",method = RequestMethod.POST)
    public ResponseJsonModel saveUserAuth(Integer roleId, String email){
          ResponseJsonModel responseModel = new ResponseJsonModel();
          List<AuthorityUsers> users = this.authorityUsersService.findUsersByEmail(email);
          if(CollectionUtils.isEmpty(users)||users.size()>1){
              responseModel.setCode(ResponseCodeConstant.ERROR_CODE);
              responseModel.setMsg("用户信息异常!");
          }else{
              AuthorityUsers user = users.get(0);
              AuthorityUserRole ur = new AuthorityUserRole();
              ur.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
              ur.setLastModifyDatetime(new Date());
              ur.setUserId(user.getId());
              ur.setRoleId(roleId);
              this.authorityUserRoleService.save(ur);
              this.authRealm.clearAllUserAuthorization();//删除全部用户的权限缓存
              this.menuCacheModel.clearMapMenuByUserId(user.getId());//删除当前员工的菜单缓存
          }

          return responseModel;
    }

    /**
     * 删除角色下用户
     * @return
     */
    @AdminLogAnn(operType = OperType.AUTH,operLevel = OperLevel.MAX,operDescribe = "删除角色下员工")
    @PermissionAnn(menuCode = "role",operCode = "delRoleUser",msg = "你没有员工授权的权限!")
    @ResponseBody
    @RequestMapping(value = "/del/user",method = RequestMethod.POST)
    public ResponseJsonModel delRoleUser(HttpServletRequest request, AuthorityUserRole ur){
        ResponseJsonModel responseModel = new ResponseJsonModel();
        ur.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
        ur.setLastModifyDatetime(new Date());
        authorityUserRoleService.deleteRoleUser(ur);//删除角色用户
        this.authRealm.clearAllUserAuthorization();//删除全部用户的权限缓存
        this.menuCacheModel.clearMapMenuByUserId(ur.getUserId());//删除当前员工的菜单缓存
        return responseModel;
    }

    /**
     * 验证当前角色名称是否唯一
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/check/role",method = RequestMethod.POST)
    public ResponseJsonModel checkRoleAttr(AuthorityRoles roles){
        ResponseJsonModel arm = new ResponseJsonModel();
        if(this.authorityRolesService.checkRoleUnique(roles)){
            //唯一
            arm.setCode(ResponseCodeConstant.SUCCESS_CODE);
        }else{
            //有重复
            arm.setCode(ResponseCodeConstant.ERROR_CODE);
            arm.setMsg("角色名称重复");
        }
        return arm;
    }
}
