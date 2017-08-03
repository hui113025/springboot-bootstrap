package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "authority_user_role")
public class AuthorityUserRole {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * last_modify_user_id
     */
    @Column(name = "last_modify_user_id")
    private Integer lastModifyUserId;

    /**
     * last_modify_datetime
     */
    @Column(name = "last_modify_datetime")
    private Date lastModifyDatetime;

    /**
     * 删除标识1删除0使用
     */
    private Integer deleted;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取last_modify_user_id
     *
     * @return last_modify_user_id - last_modify_user_id
     */
    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * 设置last_modify_user_id
     *
     * @param lastModifyUserId last_modify_user_id
     */
    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * 获取last_modify_datetime
     *
     * @return last_modify_datetime - last_modify_datetime
     */
    public Date getLastModifyDatetime() {
        return lastModifyDatetime;
    }

    /**
     * 设置last_modify_datetime
     *
     * @param lastModifyDatetime last_modify_datetime
     */
    public void setLastModifyDatetime(Date lastModifyDatetime) {
        this.lastModifyDatetime = lastModifyDatetime;
    }

    /**
     * 获取删除标识1删除0使用
     *
     * @return deleted - 删除标识1删除0使用
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识1删除0使用
     *
     * @param deleted 删除标识1删除0使用
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}