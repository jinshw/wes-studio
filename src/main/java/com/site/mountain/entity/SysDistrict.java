package com.site.mountain.entity;

import java.math.BigInteger;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description:  全国市县坐标
 * @date 2021/5/17 001714:38
 */
public class SysDistrict {
    private String id;
    private String regionName;
    private String regionCode;
    private String parentCode;
    private String weatherCityCode;
    private BigInteger longitude;
    private String latitude;
    private String marker;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getWeatherCityCode() {
        return weatherCityCode;
    }

    public void setWeatherCityCode(String weatherCityCode) {
        this.weatherCityCode = weatherCityCode;
    }

    public BigInteger getLongitude() {
        return longitude;
    }

    public void setLongitude(BigInteger longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }
}
