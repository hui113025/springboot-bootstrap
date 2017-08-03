package com.module.product.orm.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "authority_users")
public class AuthorityUsers implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 花名
     */
    @Column(name = "flower_name")
    private String flowerName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职位
     */
    private String job;

    /**
     * 员工工号
     */
    private String num;

    /**
     * 账号是否锁定(0:锁定1:正常)
     */
    @Column(name = "user_lock")
    private Integer userLock = 1;

    /**
     * 用户状态
     */
    @Column(name = "user_status")
    private Integer userStatus = 1;

    /**
     * 拓展编码
     */
    @Column(name = "expand_code")
    private String expandCode;

    /**
     * 渠道编码
     */
    @Column(name = "channel_code")
    private String channelCode;

    /**
     * 0拓展1代理
     */
    @Column(name = "channel_type")
    private Integer channelType;

    /**
     * 0公司1个人
     */
    @Column(name = "proxy_type")
    private Integer proxyType;

    /**
     * 资质证件号（营业执照/身份证）
     */
    @Column(name = "credentials_num")
    private String credentialsNum;

    /**
     * 资质照片
     */
    @Column(name = "credentials_img")
    private String credentialsImg;

    /**
     * 充值金额
     */
    @Column(name = "recharge_amount")
    private Integer rechargeAmount;

    /**
     * 消费金额
     */
    @Column(name = "consumption_amount")
    private Integer consumptionAmount;

    /**
     * 剩余金额
     */
    @Column(name = "surplus_amount")
    private Integer surplusAmount;

    /**
     * 应收账款
     */
    @Column(name = "receivable_amount")
    private Integer receivableAmount;

    /**
     * 1:超级管理员0:普通用户
     */
    private Integer administrator = 0;

    /**
     * 最后一次修改该用户信息的用户
     */
    @Column(name = "last_modify_user_id")
    private Integer lastModifyUserId;

    /**
     * 最后一次修改该用户信息的用户email
     */
    @Column(name = "last_modify_user_email")
    private String lastModifyUserEmail;

    /**
     * 创建人
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 用户创建时间
     */
    @Column(name = "create_datetime")
    private Date createDatetime;

    /**
     * 最后一次修改该用户信息的时间
     */
    @Column(name = "last_modify_datetime")
    private Date lastModifyDatetime;

    /**
     * 最后一次登录时间
     */
    @Column(name = "last_login_datetime")
    private Date lastLoginDatetime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return name - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取花名
     *
     * @return flower_name - 花名
     */
    public String getFlowerName() {
        return flowerName;
    }

    /**
     * 设置花名
     *
     * @param flowerName 花名
     */
    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取职位
     *
     * @return job - 职位
     */
    public String getJob() {
        return job;
    }

    /**
     * 设置职位
     *
     * @param job 职位
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * 获取员工工号
     *
     * @return num - 员工工号
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置员工工号
     *
     * @param num 员工工号
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取账号是否锁定(0:锁定1:正常)
     *
     * @return user_lock - 账号是否锁定(0:锁定1:正常)
     */
    public Integer getUserLock() {
        return userLock;
    }

    /**
     * 设置账号是否锁定(0:锁定1:正常)
     *
     * @param userLock 账号是否锁定(0:锁定1:正常)
     */
    public void setUserLock(Integer userLock) {
        this.userLock = userLock;
    }

    /**
     * 获取用户状态
     *
     * @return user_status - 用户状态
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取拓展编码
     *
     * @return expand_code - 拓展编码
     */
    public String getExpandCode() {
        return expandCode;
    }

    /**
     * 设置拓展编码
     *
     * @param expandCode 拓展编码
     */
    public void setExpandCode(String expandCode) {
        this.expandCode = expandCode;
    }

    /**
     * 获取渠道编码
     *
     * @return channel_code - 渠道编码
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设置渠道编码
     *
     * @param channelCode 渠道编码
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public Integer getProxyType() {
        return proxyType;
    }

    public void setProxyType(Integer proxyType) {
        this.proxyType = proxyType;
    }

    /**
     * 获取资质证件号（营业执照/身份证）
     *
     * @return credentials_num - 资质证件号（营业执照/身份证）
     */
    public String getCredentialsNum() {
        return credentialsNum;
    }

    /**
     * 设置资质证件号（营业执照/身份证）
     *
     * @param credentialsNum 资质证件号（营业执照/身份证）
     */
    public void setCredentialsNum(String credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    /**
     * 获取资质照片
     *
     * @return credentials_img - 资质照片
     */
    public String getCredentialsImg() {
        return credentialsImg;
    }

    /**
     * 设置资质照片
     *
     * @param credentialsImg 资质照片
     */
    public void setCredentialsImg(String credentialsImg) {
        this.credentialsImg = credentialsImg;
    }

    /**
     * 获取充值金额
     *
     * @return recharge_amount - 充值金额
     */
    public Integer getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 设置充值金额
     *
     * @param rechargeAmount 充值金额
     */
    public void setRechargeAmount(Integer rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 获取消费金额
     *
     * @return consumption_amount - 消费金额
     */
    public Integer getConsumptionAmount() {
        return consumptionAmount;
    }

    /**
     * 设置消费金额
     *
     * @param consumptionAmount 消费金额
     */
    public void setConsumptionAmount(Integer consumptionAmount) {
        this.consumptionAmount = consumptionAmount;
    }

    /**
     * 获取剩余金额
     *
     * @return surplus_amount - 剩余金额
     */
    public Integer getSurplusAmount() {
        return surplusAmount;
    }

    /**
     * 设置剩余金额
     *
     * @param surplusAmount 剩余金额
     */
    public void setSurplusAmount(Integer surplusAmount) {
        this.surplusAmount = surplusAmount;
    }

    /**
     * 获取应收账款
     *
     * @return receivable_amount - 应收账款
     */
    public Integer getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 设置应收账款
     *
     * @param receivableAmount 应收账款
     */
    public void setReceivableAmount(Integer receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 获取1:超级管理员0:普通用户
     *
     * @return administrator - 1:超级管理员0:普通用户
     */
    public Integer getAdministrator() {
        return administrator;
    }

    /**
     * 设置1:超级管理员0:普通用户
     *
     * @param administrator 1:超级管理员0:普通用户
     */
    public void setAdministrator(Integer administrator) {
        this.administrator = administrator;
    }

    /**
     * 获取最后一次修改该用户信息的用户
     *
     * @return last_modify_user_id - 最后一次修改该用户信息的用户
     */
    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * 设置最后一次修改该用户信息的用户
     *
     * @param lastModifyUserId 最后一次修改该用户信息的用户
     */
    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * 获取最后一次修改该用户信息的用户email
     *
     * @return last_modify_user_email - 最后一次修改该用户信息的用户email
     */
    public String getLastModifyUserEmail() {
        return lastModifyUserEmail;
    }

    /**
     * 设置最后一次修改该用户信息的用户email
     *
     * @param lastModifyUserEmail 最后一次修改该用户信息的用户email
     */
    public void setLastModifyUserEmail(String lastModifyUserEmail) {
        this.lastModifyUserEmail = lastModifyUserEmail;
    }

    /**
     * 获取创建人
     *
     * @return create_user_id - 创建人
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置创建人
     *
     * @param createUserId 创建人
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取用户创建时间
     *
     * @return create_datetime - 用户创建时间
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 设置用户创建时间
     *
     * @param createDatetime 用户创建时间
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 获取最后一次修改该用户信息的时间
     *
     * @return last_modify_datetime - 最后一次修改该用户信息的时间
     */
    public Date getLastModifyDatetime() {
        return lastModifyDatetime;
    }

    /**
     * 设置最后一次修改该用户信息的时间
     *
     * @param lastModifyDatetime 最后一次修改该用户信息的时间
     */
    public void setLastModifyDatetime(Date lastModifyDatetime) {
        this.lastModifyDatetime = lastModifyDatetime;
    }

    /**
     * 获取最后一次登录时间
     *
     * @return last_login_datetime - 最后一次登录时间
     */
    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    /**
     * 设置最后一次登录时间
     *
     * @param lastLoginDatetime 最后一次登录时间
     */
    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }
}