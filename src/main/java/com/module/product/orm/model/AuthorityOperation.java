package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "authority_operation")
public class AuthorityOperation {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作名称
     */
    private String name;

    /**
     * 操作代码
     */
    private String code;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作所属菜单ID
     */
    @Column(name = "menu_id")
    private Integer menuId;

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
     * 获取操作名称
     *
     * @return name - 操作名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置操作名称
     *
     * @param name 操作名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取操作代码
     *
     * @return code - 操作代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置操作代码
     *
     * @param code 操作代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取操作描述
     *
     * @return description - 操作描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置操作描述
     *
     * @param description 操作描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取操作所属菜单ID
     *
     * @return menu_id - 操作所属菜单ID
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * 设置操作所属菜单ID
     *
     * @param menuId 操作所属菜单ID
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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