package com.module.product.common.dto;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理树的Dto
 * Created by wangsongpeng on 2016/2/25.
 */
public class MenuTreeDto {

    private Integer id; /*菜单树ID*/
    private String name;  /*菜单树名称*/
    private String code;  /*菜单code*/
    private int enable;/*1:启用0:禁用*/
    private boolean open = false; /*是否展开*/
    private boolean parent; /*是否是父菜单*/
    private boolean checked = false; /*是否勾选*/
    private List<MenuTreeDto> children = new ArrayList<MenuTreeDto>();/*当前子节点集合*/


    public MenuTreeDto() {
    }

    public MenuTreeDto(Integer id, String name, List<MenuTreeDto> children, boolean open,boolean parent,int enable,String code) {
        this.id = id;
        this.name = name;
        this.children = children;
        this.open = open;
        this.parent = parent;
        this.enable = enable;
        this.code = code;
    }


    public MenuTreeDto(Integer id, String name,boolean parent,String code) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeDto> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MenuTreeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", enable=" + enable +
                ", open=" + open +
                ", parent=" + parent +
                ", checked=" + checked +
                ", children=" + children +
                '}';
    }
}
