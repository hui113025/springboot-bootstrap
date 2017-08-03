package com.module.product.common.search;

import com.module.product.common.bean.DataTablePageModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 员工管理DataTable搜索实体
 * Created by wangsongpeng on 2016/1/18.
 */
public class AuthorityUserSearchBean extends DataTablePageModel {

    private String  mobile;	 /* 员工的手机号 */
    private String	email;		 /* 员工的电子邮箱 */
    private String	num;		 /* 员工工号 */
    private Integer userStatus;  /*员工状态*/
    private String channelCode;
    private String name;
    private String createTime;
    private Date createDatetime;
    private Integer channelType;
    private String emailExpand;
    private Integer loginUserId;
    private Boolean isAdmin;
    private Integer searchType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDatetime() {
        Date time = null;
        try{
            if(StringUtils.isNotEmpty(createTime))
                time = DateUtils.parseDate(createTime,"YYYY-MM-DD");
        }catch (Exception e){}
        return time;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getEmailExpand() {
        return emailExpand;
    }

    public void setEmailExpand(String emailExpand) {
        this.emailExpand = emailExpand;
    }

    public Integer getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(Integer loginUserId) {
        this.loginUserId = loginUserId;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }
}
