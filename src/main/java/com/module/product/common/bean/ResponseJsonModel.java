package com.module.product.common.bean;


import com.module.product.common.constant.ResponseCodeConstant;
import com.module.product.common.constant.ResponseCodeConstant;

/**
 * 前台返回为Json时使用的模型类
 * Created by wangsongpeng on 2016/3/1.
 */
public class ResponseJsonModel {
    private String msg;//默认返回消息
    private String code = ResponseCodeConstant.SUCCESS_CODE;//默认返回消息码
    private Object result; //返回的集合数据



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResponseJsonModel{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", result=" + result +
                '}';
    }
}
