package com.module.product.common.enums;

/**
 * 操作类型
 * Created by wangsongpeng on 2016/3/17.
 */
public enum OperType {
    SYSTEM("系统操作","system"),
    AUTH("授权操作","auth"),
    CATE("分类操作","CATE"),
    FEEDBACK("意见反馈操作","FEEDBACK"),
    STUDENT("学员操作","student"),
    MESSAGE("消息操作","message"),
    EXAMINE("充值操作","examine"),
    CHANNEL("渠道操作","channel"),
    TIKU("渠道操作","tiku"),
    COUPON("卡券操作","coupon");

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
