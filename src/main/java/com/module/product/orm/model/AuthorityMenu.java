package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "authority_menu")
public class AuthorityMenu {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单URL
     */
    @Column(name = "menu_url")
    private String menuUrl;

    /**
     * 菜单是否启用(1启用0不启用)
     */
    @Column(name = "menu_enable")
    private Integer menuEnable;

    /**
     * 上级菜单ID,0为根菜单
     */
    @Column(name = "menu_parent_id")
    private Integer menuParentId;

    /**
     * 菜单的ICON图标
     */
    @Column(name = "menu_icon")
    private String menuIcon;

    /**
     * 菜单的代码
     */
    @Column(name = "menu_code")
    private String menuCode;

    /**
     * 菜单的描述
     */
    @Column(name = "menu_desc")
    private String menuDesc;

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

    @Transient
    private List<AuthorityOperation> operationList;/*菜单下的操作*/

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
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单URL
     *
     * @return menu_url - 菜单URL
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单URL
     *
     * @param menuUrl 菜单URL
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取菜单是否启用(1启用0不启用)
     *
     * @return menu_enable - 菜单是否启用(1启用0不启用)
     */
    public Integer getMenuEnable() {
        return menuEnable;
    }

    /**
     * 设置菜单是否启用(1启用0不启用)
     *
     * @param menuEnable 菜单是否启用(1启用0不启用)
     */
    public void setMenuEnable(Integer menuEnable) {
        this.menuEnable = menuEnable;
    }

    /**
     * 获取上级菜单ID,0为根菜单
     *
     * @return menu_parent_id - 上级菜单ID,0为根菜单
     */
    public Integer getMenuParentId() {
        return menuParentId;
    }

    /**
     * 设置上级菜单ID,0为根菜单
     *
     * @param menuParentId 上级菜单ID,0为根菜单
     */
    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }

    /**
     * 获取菜单的ICON图标
     *
     * @return menu_icon - 菜单的ICON图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单的ICON图标
     *
     * @param menuIcon 菜单的ICON图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 获取菜单的代码
     *
     * @return menu_code - 菜单的代码
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 设置菜单的代码
     *
     * @param menuCode 菜单的代码
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    /**
     * 获取菜单的描述
     *
     * @return menu_desc - 菜单的描述
     */
    public String getMenuDesc() {
        return menuDesc;
    }

    /**
     * 设置菜单的描述
     *
     * @param menuDesc 菜单的描述
     */
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
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

    public List<AuthorityOperation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<AuthorityOperation> operationList) {
        this.operationList = operationList;
    }
}