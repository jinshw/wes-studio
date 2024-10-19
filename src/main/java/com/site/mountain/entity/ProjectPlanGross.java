package com.site.mountain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProjectPlanGross implements Serializable {
    //主键 id
    private String grossId;

    //进度id schedule_plan_id
    private String schedulePlanId;

    //百章id  part_code
    private String partCode;

    //项目总工程量 quantities
    private Double quantities;

    //项目总工作量 workload
    private Double workload;

    //创建时间 gmt_create
    private Timestamp gmtCreate;

    //修改时间 gmt_modify
    private Timestamp gmtModify;

    //是否删除 is_delete
    private Integer isDelete;


    //附加
    //分项名称(百章)
    private PlanPart planPart;

    //年统计
    private PlanYear planYear;

    public PlanPart getPlanPart() {
        return planPart;
    }

    public void setPlanPart(PlanPart planPart) {
        this.planPart = planPart;
    }

    public PlanYear getPlanYear() {
        return planYear;
    }

    public void setPlanYear(PlanYear planYear) {
        this.planYear = planYear;
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

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public Double getQuantities() {
        return quantities;
    }

    public void setQuantities(Double quantities) {
        this.quantities = quantities;
    }

    public Double getWorkload() {
        return workload;
    }

    public void setWorkload(Double workload) {
        this.workload = workload;
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
}
