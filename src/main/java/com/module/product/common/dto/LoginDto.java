package com.module.product.common.dto;
/**
 * 登录的传输对象
 * Created by wangsongpeng on 2016/1/21.
 */
public class LoginDto {

    private boolean isSuccess = true;/*登录是否成功*/
    private String message= "成功"; /*登录响应消息*/


    public LoginDto(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
