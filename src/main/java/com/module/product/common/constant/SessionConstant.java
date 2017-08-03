package com.module.product.common.constant;
import java.io.Serializable;

/**
 * Session中的静态常量
 * Created by wangsongpeng on 2015/9/29.
 */
public class SessionConstant implements Serializable{
    public static final String LOGIN_USER = "login_user";//存放在session中的登录用户
    public static final String REDIS_SESSION_KEY_PREFIX = "shiro-session:";//redis中的sessionKey前缀
}
