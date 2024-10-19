package com.site.mountain.dto;

/**
 * 返回大屏-卡口转换的对象
 */
public class RespDpNiHikCrossingDto {
    private String name;
    private String crossingType;
    private String crossingTypeName;
    private Short intercityType;
    private String intercityTypeName;
    private String longitude;
    private String latitude;
    private String altitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCrossingType() {
        return crossingType;
    }

    public void setCrossingType(String crossingType) {
        this.crossingType = crossingType;
    }

    public String getCrossingTypeName() {
        return crossingTypeName;
    }

    public void setCrossingTypeName(String crossingTypeName) {
        this.crossingTypeName = crossingTypeName;
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
        this.intercityTypeName = intercityTypeName;
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
