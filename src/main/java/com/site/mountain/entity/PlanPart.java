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
public class PlanPart {
    //主键 id
    private String id;
    //子目号 code
    private String code;
    //子目名称 name
    private String name;
    //单位 unit
    private String unit;
    //父级id pid
    private String pid;
    //父级ids pids
    private String pids;
    //排序 num
    private String num;
    //备注 remark
    private String remark;
    //是否删除 is_delete
    private Integer isDelete;
    //gmt_create
    private Timestamp gmtCreate;
    //gmt_modify
    private Timestamp gmtModify;
    //user_create
    private String userCreate;
    //user_modify
    private String userModify;

    private List<PlanPart> children = new ArrayList<>();

    public List<PlanPart> getChildren() {
        return children;
    }

    public void setChildren(List<PlanPart> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getUserModify() {
        return userModify;
    }

    public void setUserModify(String userModify) {
        this.userModify = userModify;
    }
}
