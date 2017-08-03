package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "authority_roles")
public class AuthorityRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 上级角色ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 1:启用角色0:停用角色
     */
    private Integer enable;

    @Column(name = "last_modify_user_id")
    private Integer lastModifyUserId;

    @Column(name = "last_modify_datetime")
    private Date lastModifyDatetime;

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
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取上级角色ID
     *
     * @return parent_id - 上级角色ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置上级角色ID
     *
     * @param parentId 上级角色ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取1:启用角色0:停用角色
     *
     * @return enable - 1:启用角色0:停用角色
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * 设置1:启用角色0:停用角色
     *
     * @param enable 1:启用角色0:停用角色
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * @return last_modify_user_id
     */
    public Integer getLastModifyUserId() {
        return lastModifyUserId;
    }

    /**
     * @param lastModifyUserId
     */
    public void setLastModifyUserId(Integer lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId;
    }

    /**
     * @return last_modify_datetime
     */
    public Date getLastModifyDatetime() {
        return lastModifyDatetime;
    }

    /**
     * @param lastModifyDatetime
     */
    public void setLastModifyDatetime(Date lastModifyDatetime) {
        this.lastModifyDatetime = lastModifyDatetime;
    }
}