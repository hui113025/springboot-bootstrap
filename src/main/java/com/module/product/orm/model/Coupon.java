package com.module.product.orm.model;

import java.util.Date;
import javax.persistence.*;

public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 学院ID
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * sku分类ID
     */
    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "commodity_id")
    private Integer commodityId;

    /**
     * 有效期（天）
     */
    private Integer deadline;

    /**
     * 卡券编码
     */
    private String code;

    /**
     * -1作废0未兑换1未激活2已激活
     */
    private Integer status;

    /**
     * 学员用户手机号
     */
    private String mobile;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private Integer updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取学院ID
     *
     * @return category_id - 学院ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置学院ID
     *
     * @param categoryId 学院ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取sku分类ID
     *
     * @return sku_id - sku分类ID
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置sku分类ID
     *
     * @param skuId sku分类ID
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * @return commodity_id
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * @param commodityId
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * 获取有效期（天）
     *
     * @return deadline - 有效期（天）
     */
    public Integer getDeadline() {
        return deadline;
    }

    /**
     * 设置有效期（天）
     *
     * @param deadline 有效期（天）
     */
    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取卡券编码
     *
     * @return code - 卡券编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置卡券编码
     *
     * @param code 卡券编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取-1作废0未兑换1未激活2已激活
     *
     * @return status - -1作废0未兑换1未激活2已激活
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置-1作废0未兑换1未激活2已激活
     *
     * @param status -1作废0未兑换1未激活2已激活
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取学员用户手机号
     *
     * @return mobile - 学员用户手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置学员用户手机号
     *
     * @param mobile 学员用户手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public Integer getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_user - 更新人
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置更新人
     *
     * @param updateUser 更新人
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}