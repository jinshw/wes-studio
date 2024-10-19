package com.site.mountain.entity;


public class NtHikCrossing extends BaseNtBean {

    public NtHikCrossing(){
        poiType = "kakou";
    }

    private String indexCode;

    private String name;

    private String unitIndexCode;

    private String longitude;

    private String latitude;

    private String altitude;

    private String crossingType;

    private String crossingTypeName;

    private Short intercityType;

    private String intercityTypeName;

    private String cascadeCode;

    private String externalIndexCode;

    private String iconUrl;

    private String textColor;

    private String desc;

    public String getIndexCode() {
        return indexCode;
    }

    public void setIndexCode(String indexCode) {
        this.indexCode = indexCode == null ? null : indexCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnitIndexCode() {
        return unitIndexCode;
    }

    public void setUnitIndexCode(String unitIndexCode) {
        this.unitIndexCode = unitIndexCode == null ? null : unitIndexCode.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude == null ? null : altitude.trim();
    }

    public String getCrossingType() {
        return crossingType;
    }

    public void setCrossingType(String crossingType) {
        this.crossingType = crossingType == null ? null : crossingType.trim();
    }

    public String getCrossingTypeName() {
        return crossingTypeName;
    }

    public void setCrossingTypeName(String crossingTypeName) {
        this.crossingTypeName = crossingTypeName == null ? null : crossingTypeName.trim();
    }

    public Short getIntercityType() {
        return intercityType;
    }

    public void setIntercityType(Short intercityType) {
        this.intercityType = intercityType;
    }

    public String getIntercityTypeName() {
        return intercityTypeName;
    }

    public void setIntercityTypeName(String intercityTypeName) {
        this.intercityTypeName = intercityTypeName == null ? null : intercityTypeName.trim();
    }

    public String getCascadeCode() {
        return cascadeCode;
    }

    public void setCascadeCode(String cascadeCode) {
        this.cascadeCode = cascadeCode == null ? null : cascadeCode.trim();
    }

    public String getExternalIndexCode() {
        return externalIndexCode;
    }

    public void setExternalIndexCode(String externalIndexCode) {
        this.externalIndexCode = externalIndexCode == null ? null : externalIndexCode.trim();
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}