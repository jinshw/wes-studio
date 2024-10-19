package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProjectPlan implements Serializable {
    //主键 id
    private String id;

    //进度计划代码 schedule_plan_code
    private String schedulePlanCode;

    //进度计划名称  schedule_plan_name
    private String schedulePlanName;

    //项目代码 project_code
    private String projectCode;

    //状态(0未发布,1已发布) status
    private String status;

    //填写时间 write_date
    private String writeDate;

    //报出日期 report_date
    private String reportDate;

    //创建人- user_create
    private String userCreate;

    //备注 remark
    private String remark;

    //创建时间 gmt_create
    private Timestamp gmtCreate;

    //修改时间 gmt_modify
    private Timestamp gmtModify;

    //是否删除 is_delete
    private Integer isDelete;

    // 扩展字段
    private String pjName;//项目名称
    private String itemName;//分区名称


    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;

    public String getPjName() {
        return pjName;
    }

    public void setPjName(String pjName) {
        this.pjName = pjName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchedulePlanCode() {
        return schedulePlanCode;
    }

    public void setSchedulePlanCode(String schedulePlanCode) {
        this.schedulePlanCode = schedulePlanCode;
    }

    public String getSchedulePlanName() {
        return schedulePlanName;
    }

    public void setSchedulePlanName(String schedulePlanName) {
        this.schedulePlanName = schedulePlanName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    // 扩展字段
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
