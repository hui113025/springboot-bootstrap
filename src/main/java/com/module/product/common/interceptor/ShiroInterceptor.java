package com.module.product.common.interceptor;

import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.constant.SessionConstant;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityUsersService;
import com.module.product.common.annotation.PermissionAnn;
import com.module.product.common.bean.MenuCacheModel;
import com.module.product.common.bean.Permission;
import com.module.product.common.constant.SessionConstant;
import com.module.product.common.util.ShiroUtils;
import com.module.product.orm.model.AuthorityUsers;
import com.module.product.service.AuthorityUsersService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 * Created by wangsongpeng on 2016/3/2.
 */
@Component
public class ShiroInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private MenuCacheModel menuCache;

    @Resource
    private AuthorityUsersService usersService;

    /**
     * 在方法触发之前触发
     *
     * @param request
     * @param response
     * @param handler
     * @return true 继续下一个拦截器 false 完成之前拦截器的方法,执行下面的控制器方法
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ShiroUtils.isLogin()) {
            HandlerMethod method = (HandlerMethod) handler;//获取当前要执行的方法
            PermissionAnn p = method.getMethodAnnotation(PermissionAnn.class);//获取权限注解
            if (null != p) {
                //验证是否有权限
                if (ShiroUtils.hasNotPermission(new Permission(p.menuCode(), p.operCode()))) {
                    throw new UnauthorizedException(p.msg());
                }
            }
        } else if (ShiroUtils.getSubject().isRemembered() && ShiroUtils.getSessionUser() == null) {
            String id = (String) ShiroUtils.getSubject().getPrincipal();
            AuthorityUsers user = usersService.findById(Integer.valueOf(id));
            //ShiroUtils.getSubject().login(new UsernamePasswordToken(id, user.getPassword()));
            ShiroUtils.getSession().setAttribute(SessionConstant.LOGIN_USER, user);
        }

        return true;
    }

    /**
     * 在方法触发之后触发
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        if (modelAndView != null) {
            PermissionAnn annotation = method.getMethodAnnotation(PermissionAnn.class);
            if (null != annotation) {
                modelAndView.addObject("code", annotation.menuCode());
            }
            modelAndView.addObject("user_menu", menuCache.getMenuByUserId(ShiroUtils.getSessionUser().getId()));
        }
    }
}
