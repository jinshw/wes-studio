package com.site.mountain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtTrffassetChangeInfoMark extends BaseNtBeanNo{

    public NtTrffassetChangeInfoMark(){
        this.poiType = "ledVms";
    }

    public NtTrffassetChangeInfoMark(Integer pageSize){
        this.pageSize = pageSize;
    }

    public NtTrffassetChangeInfoMark(Integer pageSize,String type,String sectionId,String crossId){
        this.pageSize = pageSize;
        this.type = type;
        this.sectionId = sectionId;
        this.crossId = crossId;
    }

    private String id;

    private String name;

    private String code;

    private String type;

    private Double lon;

    private Double lat;

    private Double alt;

    private Integer num;

    private String location;

    private String manufacturer;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date installationTime;

    private String isStatus;

    private String datum;

    private String desc;

    private String sectionId;

    private String crossId;

    private Long deviceId;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private Long deviceType;

    private Long collStatus;

    private Long infoSource;

    private String collLocation;

    private Date modifyTime;

    private Long modifyPerson;

    private Double pitch;

    private Double roll;

    private Double heading;

    private List<MapDevice> deviceList= new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Double getAlt() {
        return alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum == null ? null : datum.trim();
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

    public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId == null ? null : crossId.trim();
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public Long getCollStatus() {
        return collStatus;
    }

    public void setCollStatus(Long collStatus) {
        this.collStatus = collStatus;
    }

    public Long getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(Long infoSource) {
        this.infoSource = infoSource;
    }

    public String getCollLocation() {
        return collLocation;
    }

    public void setCollLocation(String collLocation) {
        this.collLocation = collLocation == null ? null : collLocation.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(Long modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public List<MapDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<MapDevice> deviceList) {
        this.deviceList = deviceList;
    }
}