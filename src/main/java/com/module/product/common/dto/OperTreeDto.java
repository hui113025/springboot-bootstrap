package com.module.product.common.dto;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单下的操作树Dto
 * Created by wangsongpeng on 2016/2/25.
 */
public class OperTreeDto {

    private Integer id; /*操作ID*/
    private String name;  /*操作名称*/
    private String code;/*操作Code*/
    private boolean open = true; /*是否展开*/
    private boolean checked = false; /*是否勾选*/
    private boolean parent; /*是否是父菜单*/
    private List<OperTreeDto> children = new ArrayList<OperTreeDto>();/*子节点*/


    public OperTreeDto() {
    }

    public OperTreeDto(Integer id, String name, String code, boolean open, boolean checked, boolean parent, List<OperTreeDto> children) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.open = open;
        this.checked = checked;
        this.parent = parent;
        this.children = children;
    }

    public OperTreeDto(Integer id, String name,boolean parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public List<OperTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<OperTreeDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "OperTreeDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", open=" + open +
                ", checked=" + checked +
                ", parent=" + parent +
                ", children=" + children +
                '}';
    }
}
