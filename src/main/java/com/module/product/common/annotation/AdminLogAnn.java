package com.module.product.common.annotation;


import com.module.product.common.enums.OperLevel;
import com.module.product.common.enums.OperType;

import java.lang.annotation.*;

/**
 * 日志注解
 * Created by wangsongpeng on 2016/3/17.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminLogAnn {

    boolean enable() default true; /*是否启动日志持久化*/
    OperType operType(); /*操作类型*/
    OperLevel operLevel();/*操作级别*/
    String operDescribe();/*操作详细信息*/
}
