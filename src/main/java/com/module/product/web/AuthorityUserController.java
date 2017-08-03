package com.module.product.web;


import com.module.product.common.annotation.AdminLogAnn;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.DataTablePageModel;
import com.module.product.common.bean.ResponseJsonModel;
import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;
import com.module.product.common.search.AuthorityUserDetailBean;
import com.module.product.common.search.AuthorityUserSearchBean;
import com.module.product.common.util.EmailUtils;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityUsersService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class AuthorityUserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private AuthorityUsersService authorityUserService;

    /**
     * 跳转到员工管理页面
     *
     * @return
     */
    @PermissionAnn(menuCode = "user", msg = "你没有进入员工管理页面的权限!")
    @RequestMapping(value = "/manager")
    public String forwardEmpManager() {
        return "authority/authority_user";
    }


    /**
     * 查询员工信息
     *
     * @return
     */
    @PermissionAnn(menuCode = "user", operCode = "find", msg = "你没有员工管理页面的查询权限!")
    @ResponseBody
    @RequestMapping(value = "/serach", method = RequestMethod.POST)
    public DataTablePageModel serachEmpInfo(AuthorityUserSearchBean userSerachBean) {
        userSerachBean.setSearchType(1);
        return  authorityUserService.buildDataTablePageModel(userSerachBean);
    }


    /**
     * 根据员工ID获取当前员工数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/entity", method = RequestMethod.POST)
    public ResponseJsonModel serachEmpEntity(Integer id) {
        ResponseJsonModel responseJsonModel = new ResponseJsonModel();
        AuthorityUsers users = this.authorityUserService.findById(id);
        responseJsonModel.setResult(users);
        return responseJsonModel;
    }

    /**
     * 锁定或解锁用户
     *
     * @param user
     * @return
     */
    @PermissionAnn(menuCode = "user", operCode = "lock", msg = "你没有锁定员工的权限!")
    @ResponseBody
    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public ResponseJsonModel lockingEmp(AuthorityUsers user) {
        user.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
        user.setLastModifyUserEmail(ShiroUtils.getSessionUser().getEmail());
        user.setLastModifyDatetime(new Date());
        this.authorityUserService.LockingUser(user);
        return new ResponseJsonModel();
    }


    /**
     * 新增员工
     *
     * @param user
     * @return
     */
    @AdminLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, operDescribe = "新增员工")
    @PermissionAnn(menuCode = "user", operCode = "save", msg = "你没有新增员工的权限!")
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseJsonModel saveEmp(AuthorityUsers user) throws Exception {
        Date now = new Date();
        user.setCreateDatetime(now);
        user.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
        user.setLastModifyUserEmail(ShiroUtils.getSessionUser().getEmail());
        user.setLastModifyDatetime(now);
        String pwd = RandomStringUtils.random(8, new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A',
                'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
        user.setPassword(DigestUtils.md5Hex(pwd));
        this.authorityUserService.save(user);
        user.setPassword(pwd);
        //添加成功后,发送邮箱*/
        EmailUtils.sendPassword(user.getEmail(), pwd);
        return new ResponseJsonModel();
    }

    /**
     * 修改员工
     *
     * @param user
     * @return
     */
    @AdminLogAnn(operType = OperType.SYSTEM, operLevel = OperLevel.NORM, operDescribe = "修改员工")
    @PermissionAnn(menuCode = "user", operCode = "update", msg = "你没有修改员工的权限!")
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseJsonModel updateEmp(AuthorityUsers user) {
        user.setUserLock(user.getUserStatus());//在职解锁,离职锁定.
        user.setLastModifyUserId(ShiroUtils.getSessionUser().getId());
        user.setLastModifyUserEmail(ShiroUtils.getSessionUser().getEmail());
        user.setLastModifyDatetime(new Date());
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        }else {
            user.setPassword(null);
        }
        this.authorityUserService.update(user);
        return new ResponseJsonModel();
    }


    /**
     * 验证当前用户Email邮箱否唯一
     *
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/check/userEmail", method = RequestMethod.POST)
    public ResponseJsonModel checkEmailAttr(AuthorityUsers users) {
        ResponseJsonModel arm = new ResponseJsonModel();
        if (this.authorityUserService.checkUserEmailUnique(users)) {
            //唯一
            arm.setCode(ResponseCodeConstant.SUCCESS_CODE);
        } else {
            //有重复
            arm.setCode(ResponseCodeConstant.ERROR_CODE);
        }
        return arm;
    }

    /**
     * 验证当前用户Email邮箱否唯一
     *
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/check/userNum", method = RequestMethod.POST)
    public ResponseJsonModel checkNumAttr(AuthorityUsers users) {
        ResponseJsonModel arm = new ResponseJsonModel();
        if (this.authorityUserService.checkUserNumUnique(users)) {
            //唯一
            arm.setCode(ResponseCodeConstant.SUCCESS_CODE);
        } else {
            //有重复
            arm.setCode(ResponseCodeConstant.ERROR_CODE);
        }
        return arm;
    }

    /**
     * 验证当前用户Email邮箱否唯一
     *
     * @param
     */
    @ResponseBody
    @RequestMapping(value = "/check/userMobile", method = RequestMethod.POST)
    public ResponseJsonModel checkMobileAttr(AuthorityUsers users) {
        ResponseJsonModel arm = new ResponseJsonModel();
        if (this.authorityUserService.checkUserMobileUnique(users)) {
            //唯一
            arm.setCode(ResponseCodeConstant.SUCCESS_CODE);
        } else {
            //有重复
            arm.setCode(ResponseCodeConstant.ERROR_CODE);
        }
        return arm;
    }

    /**
     * 跳转个人页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/passWordAndInfo")
    public String passWordAndInfo(Model model) {
        model.addAttribute("user", ShiroUtils.getSessionUser());
        return "authority/userInfo";
    }

    /**
     * 修改密码
     *
     * @param authorityUserDetailBean
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassWord", method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public ResponseJsonModel updatePassWord(AuthorityUserDetailBean authorityUserDetailBean) {
        ResponseJsonModel rjm = new ResponseJsonModel();
        AuthorityUsers authorityUsers = ShiroUtils.getSessionUser();
        if (authorityUsers.getPassword().equals(DigestUtils.md5Hex(authorityUserDetailBean.getOldPassWord()))) {
            authorityUsers.setPassword(DigestUtils.md5Hex(authorityUserDetailBean.getNewPassWord()));
            this.authorityUserService.update(authorityUsers);
            rjm.setMsg("修改成功");
            rjm.setCode(ResponseCodeConstant.SUCCESS_CODE);
        } else {
            rjm.setCode(ResponseCodeConstant.ERROR_CODE);
            rjm.setMsg("原密码不正确");
        }
        return rjm;
    }
}
