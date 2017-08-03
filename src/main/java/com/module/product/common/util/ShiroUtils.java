package com.module.product.common.util;


import com.module.product.common.bean.Permission;
import com.module.product.common.constant.SessionConstant;
import com.module.product.orm.model.AuthorityUsers;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Shiro 工具类
 * Created by wangsongpeng on 2016/1/21.
 */
public class ShiroUtils {

    private final static Logger logger = LoggerFactory.getLogger(ShiroUtils.class);
    /**
     * shiro的web登录
     * @param userId
     * @param username
     * @param pwd
     */
    public static void login(long userId,String username,String pwd){
        Subject currentSubject = SecurityUtils.getSubject();
        if(!currentSubject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(userId),pwd);/*这里将USERID放入到UserNamePasswordToken对象中,保证subject的唯一性*/
            currentSubject.login(token);
        }
    }


    /**
     * shiro的web登出
     */
    public static void logout(){
        Subject currentSubject = SecurityUtils.getSubject();
        currentSubject.logout();
    }

    /**
     * 验证是否登录过
     * true 登录 false 没有登录
     * @return
     */
    public static boolean isLogin(){
         return SecurityUtils.getSubject().isAuthenticated()&&
                 null!=SecurityUtils.getSubject().getSession().getAttribute(SessionConstant.LOGIN_USER);
    }

    /**
     * 获取shiro客户主体对象
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取shiro的session,默认在web环境中托管tomcat的session
     * @return
     */
    public static Session getSession(){
        return  SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取当前登录后的用户
     * @return
     */
    public static AuthorityUsers getSessionUser(){
        return (AuthorityUsers)ShiroUtils.getSession().getAttribute(SessionConstant.LOGIN_USER);
    }

    /**
     * 验证当前用户是否有该权限集合代码的权限
     * @param ps
     * @return true:有false:没有
     */
    public static boolean hasPermissions(List<Permission> ps){
         String[] code = new String[ps.size()];
         for(int i = 0;i<ps.size();i++){
            if((code[i] = ps.get(i).toCode())==null){
                return false;
            }
         }

         boolean[] booleans =  SecurityUtils.getSubject().isPermitted(code);
         for(boolean b:booleans){
             if(!b){
                return false;
             }
         }
         return true;
    }



    /**
     * 验证当前用户是否有该权限代码的权限
     * @return true:有false:没有
     */
    public static boolean hasPermission(Permission p){
        String code =  p.toCode();
        if(code == null){
            return false;
        }
        return SecurityUtils.getSubject().isPermitted(code);
    }



    /**
     * 验证当前用户是否没有该权限代码的权限
     * @return true:没有false:有
     */
    public static boolean hasNotPermission(Permission p){
        String code =  p.toCode();
        if(code == null){
            throw new NullPointerException("权限代码出现空指针!");
        }
        return !SecurityUtils.getSubject().isPermitted(code);
    }


    /**
     * 验证当前用户是否没有该权限集合代码的权限
     * @param ps
     * @return true:没有 false:有
     */
    public static boolean hasNotPermissions(List<Permission> ps){
        String[] code = new String[ps.size()];
        for(int i = 0;i<ps.size();i++){
            if((code[i] = ps.get(i).toCode())==null){
               throw new NullPointerException("权限代码出现空指针!");
            }
        }
        boolean[] booleans =  SecurityUtils.getSubject().isPermitted(code);
        for(boolean b:booleans){
            if(b){
                return false;
            }
        }
        return true;
    }



}


