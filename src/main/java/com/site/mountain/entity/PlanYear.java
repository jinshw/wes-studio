package com.site.mountain.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:
 * @date 2021/5/27 002713:55
 */
public class PlanYear {
    //主键 id
    private String id;
    //年 year
    private String year;
    // gross_id
    private String grossId;
    // schedule_plan_id
    private String schedulePlanId;
    //工程量 year_quantities
    private Double yearQuantities;
    //工程量占项目总量百分比 percent_quantities
    private Double percentQuantities;
    //工作量 year_workload
    private Double yearWorkload;
    //工作量占项目总量百分比 percent_workload
    private Double percentWorkload;
    //是否删除 is_delete
    private Integer isDelete;
    //gmt_create
    private Timestamp gmtCreate;
    //gmt_modify
    private Timestamp gmtModify;
    //user_create
    private String userCreate;

    //附加
    //月统计
    private PlanMonth planMonth;

    public PlanMonth getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(PlanMonth planMonth) {
        this.planMonth = planMonth;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGrossId() {
        return grossId;
    }

    public void setGrossId(String grossId) {
        this.grossId = grossId;
    }

    public String getSchedulePlanId() {
        return schedulePlanId;
    }

    public void setSchedulePlanId(String schedulePlanId) {
        this.schedulePlanId = schedulePlanId;
    }

    public Double getYearQuantities() {
        return yearQuantities;
    }

    public void setYearQuantities(Double yearQuantities) {
        this.yearQuantities = yearQuantities;
    }

    public Double getPercentQuantities() {
        return percentQuantities;
    }

    public void setPercentQuantities(Double percentQuantities) {
        this.percentQuantities = percentQuantities;
    }

    public Double getYearWorkload() {
        return yearWorkload;
    }

    public void setYearWorkload(Double yearWorkload) {
        this.yearWorkload = yearWorkload;
    }

    public Double getPercentWorkload() {
        return percentWorkload;
    }

    public void setPercentWorkload(Double percentWorkload) {
        this.percentWorkload = percentWorkload;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }
}
