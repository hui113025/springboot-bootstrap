package com.module.product.common.util;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Commons email邮箱工具类
 * Created by wangsongpeng on 2016/3/29.
 */
public class EmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

    /**
     * 员工添加成功后发送初始化密码到对啊邮箱
     */
    public static void sendPassword(final String email, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleEmail simpleEmail = new SimpleEmail();
                try {
                    simpleEmail.setHostName("xxx.263xmail.com");//邮件服务器地址
                    simpleEmail.setSmtpPort(25);//端口号
                    simpleEmail.setAuthentication("xxx@163.com", "2018");//邮件用户名密码
                    simpleEmail.setFrom("xxx@163.com");//邮件发送人
                    simpleEmail.addTo(email);//邮件接收人
                    simpleEmail.setSubject("新员工密码(系统邮件请勿回复)");//邮件主题
                    simpleEmail.setMsg("恭喜您成为xxx网新员工!您的后台系统默认密码为:" + password + "." + "请登录http://back.xxx.com后台系统后尽快修改自己的密码.");//邮件正文
                    simpleEmail.send();//发送
                } catch (EmailException e) {
                    logger.error("发送给" + email + "密码邮件时,异常", e);
                }
            }
        }).start();
    }
}
