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
public class PlanMonth {
    //主键 id
    private String id;
    // schedule_plan_id
    private String schedulePlanId;
    // schedule_plan_year_id
    private String schedulePlanYearId;
    //月 month
    private String month;
    //月计划工程量 month_quantities
    private Double monthQuantities;
    //月计划工程量占年计划百分比 month_percent_quantities
    private Double monthPercentQuantities;
    //月计划工作量 month_workload
    private Double monthWorkload;
    //月计划工作量占年计划百分比 month_percent_workload
    private Double monthPercentWorkload;

    //本月完成工程量 month_quantities_completed
    private Double monthQuantitiesCompleted;
    //本月完成工程量占月计划百分比 month_percent_quantities_completed
    private Double monthPercentQuantitiesCompleted;
    //本月完成工作量 month_workload_completed
    private Double monthWorkloadCompleted;
    //本月完成工作量占月计划百分比 month_percent_workload_completed
    private Double monthPercentWorkloadCompleted;

    //年完成工程量累计 year_quantities_total
    private Double yearQuantitiesTotal;
    //年工程量累计完成百分比 year_quantities_percent
    private Double yearQuantitiesPercent;
    //年累计完成工作量 year_workload_total
    private Double yearWorkloadTotal;
    //本年已完成工作量占年计划百分比 year_workload_percent_total
    private Double yearWorkloadPercentTotal;

    //项目工程量累计完成 project_quantities_total
    private Double projectQuantitiesTotal;
    //项目工程量累计完成百分比  project_quantities_percent
    private Double projectQuantitiesPercent;
    //项目累计完成工作量 project_workload_total
    private Double projectWorkloadTotal;
    //项目工作量累计完成百分比 project_workload_percent
    private Double projectWorkloadPercent;

    //是否删除 is_delete
    private Integer isDelete;
    //gmt_create
    private Timestamp gmtCreate;
    //gmt_modify
    private Timestamp gmtModify;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchedulePlanId() {
        return schedulePlanId;
    }

    public void setSchedulePlanId(String schedulePlanId) {
        this.schedulePlanId = schedulePlanId;
    }

    public String getSchedulePlanYearId() {
        return schedulePlanYearId;
    }

    public void setSchedulePlanYearId(String schedulePlanYearId) {
        this.schedulePlanYearId = schedulePlanYearId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getMonthQuantities() {
        return monthQuantities;
    }

    public void setMonthQuantities(Double monthQuantities) {
        this.monthQuantities = monthQuantities;
    }

    public Double getMonthPercentQuantities() {
        return monthPercentQuantities;
    }

    public void setMonthPercentQuantities(Double monthPercentQuantities) {
        this.monthPercentQuantities = monthPercentQuantities;
    }

    public Double getMonthWorkload() {
        return monthWorkload;
    }

    public void setMonthWorkload(Double monthWorkload) {
        this.monthWorkload = monthWorkload;
    }

    public Double getMonthPercentWorkload() {
        return monthPercentWorkload;
    }

    public void setMonthPercentWorkload(Double monthPercentWorkload) {
        this.monthPercentWorkload = monthPercentWorkload;
    }

    public Double getMonthQuantitiesCompleted() {
        return monthQuantitiesCompleted;
    }

    public void setMonthQuantitiesCompleted(Double monthQuantitiesCompleted) {
        this.monthQuantitiesCompleted = monthQuantitiesCompleted;
    }

    public Double getMonthPercentQuantitiesCompleted() {
        return monthPercentQuantitiesCompleted;
    }

    public void setMonthPercentQuantitiesCompleted(Double monthPercentQuantitiesCompleted) {
        this.monthPercentQuantitiesCompleted = monthPercentQuantitiesCompleted;
    }

    public Double getMonthWorkloadCompleted() {
        return monthWorkloadCompleted;
    }

    public void setMonthWorkloadCompleted(Double monthWorkloadCompleted) {
        this.monthWorkloadCompleted = monthWorkloadCompleted;
    }

    public Double getMonthPercentWorkloadCompleted() {
        return monthPercentWorkloadCompleted;
    }

    public void setMonthPercentWorkloadCompleted(Double monthPercentWorkloadCompleted) {
        this.monthPercentWorkloadCompleted = monthPercentWorkloadCompleted;
    }

    public Double getYearQuantitiesTotal() {
        return yearQuantitiesTotal;
    }

    public void setYearQuantitiesTotal(Double yearQuantitiesTotal) {
        this.yearQuantitiesTotal = yearQuantitiesTotal;
    }

    public Double getYearQuantitiesPercent() {
        return yearQuantitiesPercent;
    }

    public void setYearQuantitiesPercent(Double yearQuantitiesPercent) {
        this.yearQuantitiesPercent = yearQuantitiesPercent;
    }

    public Double getYearWorkloadTotal() {
        return yearWorkloadTotal;
    }

    public void setYearWorkloadTotal(Double yearWorkloadTotal) {
        this.yearWorkloadTotal = yearWorkloadTotal;
    }

    public Double getYearWorkloadPercentTotal() {
        return yearWorkloadPercentTotal;
    }

    public void setYearWorkloadPercentTotal(Double yearWorkloadPercentTotal) {
        this.yearWorkloadPercentTotal = yearWorkloadPercentTotal;
    }

    public Double getProjectQuantitiesTotal() {
        return projectQuantitiesTotal;
    }

    public void setProjectQuantitiesTotal(Double projectQuantitiesTotal) {
        this.projectQuantitiesTotal = projectQuantitiesTotal;
    }

    public Double getProjectQuantitiesPercent() {
        return projectQuantitiesPercent;
    }

    public void setProjectQuantitiesPercent(Double projectQuantitiesPercent) {
        this.projectQuantitiesPercent = projectQuantitiesPercent;
    }

    public Double getProjectWorkloadTotal() {
        return projectWorkloadTotal;
    }

    public void setProjectWorkloadTotal(Double projectWorkloadTotal) {
        this.projectWorkloadTotal = projectWorkloadTotal;
    }

    public Double getProjectWorkloadPercent() {
        return projectWorkloadPercent;
    }

    public void setProjectWorkloadPercent(Double projectWorkloadPercent) {
        this.projectWorkloadPercent = projectWorkloadPercent;
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





}
