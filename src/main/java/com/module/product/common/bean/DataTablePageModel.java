package com.module.product.common.bean;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.module.product.common.util.DateUtils.getFirstDateOfDay;
import static com.module.product.common.util.DateUtils.getLastDateOfDay;


public class DataTablePageModel{
    /**********客户端发送的参数************/
    private int draw;/*请求次数计数器每次发送给服务器后又原封返回*/
    private int start;/*分页的起始位置,dataTable自动计算好索引了*/
    private int length;/*分页每页显示的数量*/
    /**********服务器返回的数据************/
    private List<?> data = new ArrayList<>(); /*返回的分页数据*/
    private long recordsTotal;/*数据库里总共记录数*/
    private long recordsFiltered;/*过滤后的记录数*/
    private String error;/*错误来描述服务器出了问题后的友好提示 */

    // 时间控件查询参数
    private String dateString;
    private Date startDate;
    private Date endDate;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        if (StringUtils.isNotBlank(dateString)) {
            String date[] = dateString.split("/");
            try {
                startDate =  com.module.product.common.util.DateUtils.getFirstDateOfDay(DateUtils.parseDate(date[0], "yyyy-MM-dd"));
                endDate =  com.module.product.common.util.DateUtils.getLastDateOfDay(DateUtils.parseDate(date[1], "yyyy-MM-dd"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DataTablePageModel{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", data=" + data +
                ", recordsTotal=" + recordsTotal +
                ", recordsFiltered=" + recordsFiltered +
                ", error='" + error + '\'' +
                '}';
    }
}
