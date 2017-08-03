package com.module.product.common.annotation;

import com.module.product.common.constant.PermissionConstant;

import java.lang.annotation.*;

/**
 * 权限注解
 * Created by wangsongpeng on 2016/3/2.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PermissionAnn {
    String menuCode(); /*菜单权限码*/
    String operCode() default PermissionConstant.DEFAULT_OPER; /*操作权限码*/
    String msg() default "没有权限!";/*没有权限时提示消息*/
}

