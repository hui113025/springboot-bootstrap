package com.module.product.web;


import com.module.product.common.dto.LoginDto;
import com.module.product.common.util.ShiroUtils;
import com.module.product.service.AuthorityUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录控制器
 * 只能没有登录的用户可以访问
 * Created by wangsongpeng on 2015/11/11.
 */
@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AuthorityUsersService authorityUsers;/*用户业务逻辑对象*/

    @RequestMapping(value = "/login")
    public String redirectLogin(HttpServletRequest request, HttpServletResponse response) {
        //如果已经登录过,跳转到首页
        if (ShiroUtils.isLogin()) {
            return "redirect:/";
        }
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public LoginDto checkLogin(String email, String password, boolean isrem) {
        LoginDto loginDto = authorityUsers.checkLogin(email, password, isrem);
        return loginDto;
    }
}
