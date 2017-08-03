package com.module.product.orm.model;

import  javax.persistence.*;
import java.util.Date;

@Table(name = "admin_log")
public class AdminLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作人ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作人姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 操作名称
     */
    @Column(name = "oper_name")
    private String operName;

    /**
     * 操作类型
     */
    @Column(name = "oper_type")
    private String operType;

    /**
     * 操作描述
     */
    @Column(name = "oper_describe")
    private String operDescribe;

    /**
     * 操作级别
     */
    @Column(name = "oper_level")
    private Integer operLevel;

    /**
     * 操作时间
     */
    @Column(name = "oper_time")
    private Date operTime;

    /**
     * 操作参数
     */
    @Column(name = "oper_param")
    private String operParam;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取操作人ID
     *
     * @return user_id - 操作人ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置操作人ID
     *
     * @param userId 操作人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取操作人姓名
     *
     * @return user_name - 操作人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置操作人姓名
     *
     * @param userName 操作人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作名称
     *
     * @return oper_name - 操作名称
     */
    public String getOperName() {
        return operName;
    }

    /**
     * 设置操作名称
     *
     * @param operName 操作名称
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * 获取操作类型
     *
     * @return oper_type - 操作类型
     */
    public String getOperType() {
        return operType;
    }

    /**
     * 设置操作类型
     *
     * @param operType 操作类型
     */
    public void setOperType(String operType) {
        this.operType = operType;
    }

    /**
     * 获取操作描述
     *
     * @return oper_describe - 操作描述
     */
    public String getOperDescribe() {
        return operDescribe;
    }

    /**
     * 设置操作描述
     *
     * @param operDescribe 操作描述
     */
    public void setOperDescribe(String operDescribe) {
        this.operDescribe = operDescribe;
    }

    /**
     * 获取操作级别
     *
     * @return oper_level - 操作级别
     */
    public Integer getOperLevel() {
        return operLevel;
    }

    /**
     * 设置操作级别
     *
     * @param operLevel 操作级别
     */
    public void setOperLevel(Integer operLevel) {
        this.operLevel = operLevel;
    }

    /**
     * 获取操作时间
     *
     * @return oper_time - 操作时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 设置操作时间
     *
     * @param operTime 操作时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    /**
     * 获取操作参数
     *
     * @return oper_param - 操作参数
     */
    public String getOperParam() {
        return operParam;
    }

    /**
     * 设置操作参数
     *
     * @param operParam 操作参数
     */
    public void setOperParam(String operParam) {
        this.operParam = operParam;
    }
}