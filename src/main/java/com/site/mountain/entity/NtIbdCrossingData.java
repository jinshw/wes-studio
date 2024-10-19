package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtIbdCrossingData extends BaseNtBean{
    public NtIbdCrossingData() {
        poiType = "crossing";
    }

    public NtIbdCrossingData(Integer pageSize,String crossId) {
        this.pageSize = pageSize;
        this.crossId = crossId;
    }

    private String crossId;

    private String name;

    private String crossCode;

    private String crossType;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private String polygon;

    private String lineColor;

    private String lineWidth;

    private String lineStyle;

    private String outlineColor;

    private String outlineWidth;

    private String outlineStyle;

    private String sectionId;

    private String roadId;

    private String polygonCenter;

    private List<NtIbdTrafficPole> trafficPoleList = new ArrayList<>();

    public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId == null ? null : crossId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCrossCode() {
        return crossCode;
    }

    public void setCrossCode(String crossCode) {
        this.crossCode = crossCode == null ? null : crossCode.trim();
    }

    public String getCrossType() {
        return crossType;
    }

    public void setCrossType(String crossType) {
        this.crossType = crossType == null ? null : crossType.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }

    public Long getOptPerson() {
        return optPerson;
    }

    public void setOptPerson(Long optPerson) {
        this.optPerson = optPerson;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon == null ? null : polygon.trim();
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor == null ? null : lineColor.trim();
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth == null ? null : lineWidth.trim();
    }

    public String getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle == null ? null : lineStyle.trim();
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor == null ? null : outlineColor.trim();
    }

    public String getOutlineWidth() {
        return outlineWidth;
    }

    public void setOutlineWidth(String outlineWidth) {
        this.outlineWidth = outlineWidth == null ? null : outlineWidth.trim();
    }

    public String getOutlineStyle() {
        return outlineStyle;
    }

    public void setOutlineStyle(String outlineStyle) {
        this.outlineStyle = outlineStyle == null ? null : outlineStyle.trim();
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId == null ? null : roadId.trim();
    }

    public String getPolygonCenter() {
        return polygonCenter;
    }

    public void setPolygonCenter(String polygonCenter) {
        this.polygonCenter = polygonCenter;
    }

    public List<NtIbdTrafficPole> getTrafficPoleList() {
        return trafficPoleList;
    }

    public void setTrafficPoleList(List<NtIbdTrafficPole> trafficPoleList) {
        this.trafficPoleList = trafficPoleList;
    }
}