package com.site.mountain.dto;

/**
 * 返回大屏-视频转换的对象
 */
public class RespDpNiHikCameraDto {
    public String name;
    public String installPlace;
    public Short cameraType;
    public String cameraTypeName;
    public Short chanNum;
    public Short status;
    public String statusName;
    public String desc;
    public String longitude;
    public String latitude;
    public String altitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace;
    }

    public Short getCameraType() {
        return cameraType;
    }

    public void setCameraType(Short cameraType) {
        this.cameraType = cameraType;
    }

    public String getCameraTypeName() {
        return cameraTypeName;
    }

    public void setCameraTypeName(String cameraTypeName) {
        this.cameraTypeName = cameraTypeName;
    }

    public Short getChanNum() {
        return chanNum;
    }

    public void setChanNum(Short chanNum) {
        this.chanNum = chanNum;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
}
