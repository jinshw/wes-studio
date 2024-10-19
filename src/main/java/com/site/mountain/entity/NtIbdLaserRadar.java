package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtIbdLaserRadar extends BaseNtBean{

    public NtIbdLaserRadar(){
        poiType = "laserRadar";
    }

    public NtIbdLaserRadar(Integer pageSize){
        this.pageSize = pageSize;
    }

    private String laserRadarId;

    private String name;

    private String radarCode;

    private String radarType;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private String sectionId;

    private Long deviceId;

    private String location;

    private String manufacturer;

    private Date installationTime;

    private String isStatus;

    private List<MapDevice> deviceList= new ArrayList<>();

    public String getLaserRadarId() {
        return laserRadarId;
    }

    public void setLaserRadarId(String laserRadarId) {
        this.laserRadarId = laserRadarId == null ? null : laserRadarId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRadarCode() {
        return radarCode;
    }

    public void setRadarCode(String radarCode) {
        this.radarCode = radarCode == null ? null : radarCode.trim();
    }

    public String getRadarType() {
        return radarType;
    }

    public void setRadarType(String radarType) {
        this.radarType = radarType == null ? null : radarType.trim();
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

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public Date getInstallationTime() {
        return installationTime;
    }

    public void setInstallationTime(Date installationTime) {
        this.installationTime = installationTime;
    }

    public String getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(String isStatus) {
        this.isStatus = isStatus == null ? null : isStatus.trim();
    }

    public List<MapDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<MapDevice> deviceList) {
        this.deviceList = deviceList;
    }
}