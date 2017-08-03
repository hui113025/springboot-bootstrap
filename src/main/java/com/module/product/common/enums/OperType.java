package com.module.product.common.enums;

/**
 * 操作类型
 * Created by wangsongpeng on 2016/3/17.
 */
public enum OperType {
    SYSTEM("系统操作","system"),
    AUTH("授权操作","auth"),
    FEEDBACK("意见反馈操作","FEEDBACK"),
    MESSAGE("消息操作","message");

    OperType(String name,String value) {
        this.name = name;
        this.value =value;
    }

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println(OperType.AUTH.getName());
    }

}
