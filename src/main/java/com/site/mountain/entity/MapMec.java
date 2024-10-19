package com.site.mountain.entity;

import java.util.Date;
import java.util.List;

public class MapMec extends BaseMapBean{
    private Long mecId;

    private String mecName;

    private Float effectiveRadius;

    private Integer status;

    private String code;

    private String projectName;

    private Double lon;

    private Double lat;

    private String connectUrl;

    private Integer isDelete;

    private Date optTime;

    private Long optPerson;

    private String remark;

    private List<MapData> mecDataList;

    public List<MapData> getMecDataList() {
        return mecDataList;
    }

    public void setMecDataList(List<MapData> mecDataList) {
        this.mecDataList = mecDataList;
    }

    public Long getMecId() {
        return mecId;
    }

    public void setMecId(Long mecId) {
        this.mecId = mecId;
    }

    public String getMecName() {
        return mecName;
    }

    public void setMecName(String mecName) {
        this.mecName = mecName == null ? null : mecName.trim();
    }

    public Float getEffectiveRadius() {
        return effectiveRadius;
    }

    public void setEffectiveRadius(Float effectiveRadius) {
        this.effectiveRadius = effectiveRadius;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getConnectUrl() {
        return connectUrl;
    }

    public void setConnectUrl(String connectUrl) {
        this.connectUrl = connectUrl == null ? null : connectUrl.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public Long getOptPerson() {
        return optPerson;
    }

    public void setOptPerson(Long optPerson) {
        this.optPerson = optPerson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}