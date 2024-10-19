package com.site.mountain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NtActplanAssetData extends BaseNtBean{

    public NtActplanAssetData(){
        poiType = "actplanAssetData";
    }

    public String assetDataId;

    public String name;

    public String type;

    public Integer num;

    public String longitude;

    public String latitude;

    public String altitude;

    public String iconUrl;

    public String textColor;

    public String policeMan;

    public String policeNum;

    public String contactInfo;

    public String contactMan;

    public String stationNum;

    public String department;

    public String rank;

    public String installPlace;

    public String parkingNum;

    public String chargingTime;

    public String chargingStandard;

    public String polygon;

    public String isDel;

    public Long optPerson;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    public Date optTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    public Date createTime;

    public String desc;

    public String actplanId;

    public String lineColor;
    public String lineWidth;
    public String lineStyle;
    public String outlineColor;
    public String outlineWidth;
    public String outlineStyle;

    public Double lineLength;
    public String subPoints;

    public String getAssetDataId() {
        return assetDataId;
    }

    public void setAssetDataId(String assetDataId) {
        this.assetDataId = assetDataId == null ? null : assetDataId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor == null ? null : textColor.trim();
    }

    public String getPoliceMan() {
        return policeMan;
    }

    public void setPoliceMan(String policeMan) {
        this.policeMan = policeMan == null ? null : policeMan.trim();
    }

    public String getPoliceNum() {
        return policeNum;
    }

    public void setPoliceNum(String policeNum) {
        this.policeNum = policeNum == null ? null : policeNum.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan == null ? null : contactMan.trim();
    }

    public String getStationNum() {
        return stationNum;
    }

    public void setStationNum(String stationNum) {
        this.stationNum = stationNum == null ? null : stationNum.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank == null ? null : rank.trim();
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace == null ? null : installPlace.trim();
    }

    public String getParkingNum() {
        return parkingNum;
    }

    public void setParkingNum(String parkingNum) {
        this.parkingNum = parkingNum == null ? null : parkingNum.trim();
    }

    public String getChargingTime() {
        return chargingTime;
    }

    public void setChargingTime(String chargingTime) {
        this.chargingTime = chargingTime == null ? null : chargingTime.trim();
    }

    public String getChargingStandard() {
        return chargingStandard;
    }

    public void setChargingStandard(String chargingStandard) {
        this.chargingStandard = chargingStandard == null ? null : chargingStandard.trim();
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon == null ? null : polygon.trim();
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

    public String getActplanId() {
        return actplanId;
    }

    public void setActplanId(String actplanId) {
        this.actplanId = actplanId;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle;
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor;
    }

    public String getOutlineWidth() {
        return outlineWidth;
    }

    public void setOutlineWidth(String outlineWidth) {
        this.outlineWidth = outlineWidth;
    }

    public String getOutlineStyle() {
        return outlineStyle;
    }

    public void setOutlineStyle(String outlineStyle) {
        this.outlineStyle = outlineStyle;
    }

    public Double getLineLength() {
        return lineLength;
    }

    public void setLineLength(Double lineLength) {
        this.lineLength = lineLength;
    }

    public String getSubPoints() {
        return subPoints;
    }

    public void setSubPoints(String subPoints) {
        this.subPoints = subPoints;
    }
}