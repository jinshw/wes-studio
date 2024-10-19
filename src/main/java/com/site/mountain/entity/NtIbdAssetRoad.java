package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtIbdAssetRoad extends BaseNtBean{
    public NtIbdAssetRoad() {
        poiType = "road";
    }

    public NtIbdAssetRoad(Integer pageSize,String roadId) {
        this.pageSize = pageSize;
        this.roadId = roadId;
    }

    private String roadId;

    private String name;

    private String roadCode;

    private String roadType;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private String polygon;

    private String polygonCenter;

    private String lineColor;

    private String lineWidth;

    private String lineStyle;

    private String outlineColor;

    private String outlineWidth;

    private String outlineStyle;

    private String locationStart;

    private String locationEnd;

    private String roadLevel;

    private String areaCode;

    private String roadLen;

    List<NtIbdAssetSection> sectionList = new ArrayList<>();

    List<NtIbdCrossingData> crossingList = new ArrayList<>();

    public String getRoadId() {
        return roadId;
    }

    public void setRoadId(String roadId) {
        this.roadId = roadId == null ? null : roadId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoadCode() {
        return roadCode;
    }

    public void setRoadCode(String roadCode) {
        this.roadCode = roadCode == null ? null : roadCode.trim();
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType == null ? null : roadType.trim();
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

    public String getPolygonCenter() {
        return polygonCenter;
    }

    public void setPolygonCenter(String polygonCenter) {
        this.polygonCenter = polygonCenter == null ? null : polygonCenter.trim();
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

    public String getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(String locationStart) {
        this.locationStart = locationStart == null ? null : locationStart.trim();
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd == null ? null : locationEnd.trim();
    }

    public String getRoadLevel() {
        return roadLevel;
    }

    public void setRoadLevel(String roadLevel) {
        this.roadLevel = roadLevel == null ? null : roadLevel.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getRoadLen() {
        return roadLen;
    }

    public void setRoadLen(String roadLen) {
        this.roadLen = roadLen == null ? null : roadLen.trim();
    }

    public List<NtIbdAssetSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<NtIbdAssetSection> sectionList) {
        this.sectionList = sectionList;
    }

    public List<NtIbdCrossingData> getCrossingList() {
        return crossingList;
    }

    public void setCrossingList(List<NtIbdCrossingData> crossingList) {
        this.crossingList = crossingList;
    }
}