package com.module.product.orm.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "authority_permission")
public class AuthorityPermission {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 资源ID
     */
    @Column(name = "resources_id")
    private Integer resourcesId;

    /**
     * 资源代码
     */
    @Column(name = "resources_code")
    private String resourcesCode;

    /**
     * 操作ID
     */
    @Column(name = "operation_id")
    private Integer operationId;

    /**
     * 操作代码
     */
    @Column(name = "operation_code")
    private String operationCode;

    /**
     * 分类id
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * 权限代码{菜单code:操作code:categoryId}
     */
    @Column(name = "permission_code")
    private String permissionCode;

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
     * 获取资源ID
     *
     * @return resources_id - 资源ID
     */
    public Integer getResourcesId() {
        return resourcesId;
    }

    /**
     * 设置资源ID
     *
     * @param resourcesId 资源ID
     */
    public void setResourcesId(Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    /**
     * 获取资源代码
     *
     * @return resources_code - 资源代码
     */
    public String getResourcesCode() {
        return resourcesCode;
    }

    /**
     * 设置资源代码
     *
     * @param resourcesCode 资源代码
     */
    public void setResourcesCode(String resourcesCode) {
        this.resourcesCode = resourcesCode;
    }

    /**
     * 获取操作ID
     *
     * @return operation_id - 操作ID
     */
    public Integer getOperationId() {
        return operationId;
    }

    /**
     * 设置操作ID
     *
     * @param operationId 操作ID
     */
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    /**
     * 获取操作代码
     *
     * @return operation_code - 操作代码
     */
    public String getOperationCode() {
        return operationCode;
    }

    /**
     * 设置操作代码
     *
     * @param operationCode 操作代码
     */
    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    /**
     * 获取分类id
     *
     * @return category_id - 分类id
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置分类id
     *
     * @param categoryId 分类id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取权限代码{菜单code:操作code:categoryId}
     *
     * @return permission_code - 权限代码{菜单code:操作code:categoryId}
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 设置权限代码{菜单code:操作code:categoryId}
     *
     * @param permissionCode 权限代码{菜单code:操作code:categoryId}
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
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

    public String getCode(){
        return this.getResourcesCode()+":"+this.getOperationCode();
    }
}