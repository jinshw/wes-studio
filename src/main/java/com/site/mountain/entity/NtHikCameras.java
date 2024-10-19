package com.site.mountain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.site.mountain.entity.BaseNtBean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtHikCameras extends BaseNtBean {
    public NtHikCameras(){
        poiType = "shexiangtou";
    }

    public NtHikCameras(Integer pageSize){
        this.pageSize = pageSize;
    }

    private String cameraIndexCode;

    private String gbIndexCode;

    private String name;

    private String deviceIndexCode;

    private String longitude;

    private String latitude;

    private String altitude;

    private Integer pixel;

    private Short cameraType;

    private String cameraTypeName;

    private String installPlace;

    private String matrixCode;

    private Short chanNum;

    private String viewshed;

    private String capabilitySet;

    private String capabilitySetName;

    private String intelligentSet;

    private String intelligentSetName;

    private String recordLocation;

    private String recordLocationName;

    private Short ptzController;

    private String ptzControllerName;

    private String deviceResourceType;

    private String deviceResourceTypeName;

    private Short channelType;

    private String channelTypeName;

    private Short transType;

    private String transTypeName;

    private String updateTime;

    private String unitIndexCode;

    private String treatyType;

    private String treatyTypeName;

    private String createTime;

    private Short status;

    private String statusName;

    private String isScope;

    private String iconUrl;

    private String textColor;

    private String desc;

    private String crossId;

    private BigInteger deviceId;

    private String poleId;

    private String location;

    private String manufacturer;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date installationTime;

    private String isStatus;

    private String datum;

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

    public String getCameraIndexCode() {
        return cameraIndexCode;
    }

    public void setCameraIndexCode(String cameraIndexCode) {
        this.cameraIndexCode = cameraIndexCode == null ? null : cameraIndexCode.trim();
    }

    public String getGbIndexCode() {
        return gbIndexCode;
    }

    public void setGbIndexCode(String gbIndexCode) {
        this.gbIndexCode = gbIndexCode == null ? null : gbIndexCode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDeviceIndexCode() {
        return deviceIndexCode;
    }

    public void setDeviceIndexCode(String deviceIndexCode) {
        this.deviceIndexCode = deviceIndexCode == null ? null : deviceIndexCode.trim();
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

    public Integer getPixel() {
        return pixel;
    }

    public void setPixel(Integer pixel) {
        this.pixel = pixel;
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
        this.cameraTypeName = cameraTypeName == null ? null : cameraTypeName.trim();
    }

    public String getInstallPlace() {
        return installPlace;
    }

    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace == null ? null : installPlace.trim();
    }

    public String getMatrixCode() {
        return matrixCode;
    }

    public void setMatrixCode(String matrixCode) {
        this.matrixCode = matrixCode == null ? null : matrixCode.trim();
    }

    public Short getChanNum() {
        return chanNum;
    }

    public void setChanNum(Short chanNum) {
        this.chanNum = chanNum;
    }

    public String getViewshed() {
        return viewshed;
    }

    public void setViewshed(String viewshed) {
        this.viewshed = viewshed == null ? null : viewshed.trim();
    }

    public String getCapabilitySet() {
        return capabilitySet;
    }

    public void setCapabilitySet(String capabilitySet) {
        this.capabilitySet = capabilitySet == null ? null : capabilitySet.trim();
    }

    public String getCapabilitySetName() {
        return capabilitySetName;
    }

    public void setCapabilitySetName(String capabilitySetName) {
        this.capabilitySetName = capabilitySetName == null ? null : capabilitySetName.trim();
    }

    public String getIntelligentSet() {
        return intelligentSet;
    }

    public void setIntelligentSet(String intelligentSet) {
        this.intelligentSet = intelligentSet == null ? null : intelligentSet.trim();
    }

    public String getIntelligentSetName() {
        return intelligentSetName;
    }

    public void setIntelligentSetName(String intelligentSetName) {
        this.intelligentSetName = intelligentSetName == null ? null : intelligentSetName.trim();
    }

    public String getRecordLocation() {
        return recordLocation;
    }

    public void setRecordLocation(String recordLocation) {
        this.recordLocation = recordLocation == null ? null : recordLocation.trim();
    }

    public String getRecordLocationName() {
        return recordLocationName;
    }

    public void setRecordLocationName(String recordLocationName) {
        this.recordLocationName = recordLocationName == null ? null : recordLocationName.trim();
    }

    public Short getPtzController() {
        return ptzController;
    }

    public void setPtzController(Short ptzController) {
        this.ptzController = ptzController;
    }

    public String getPtzControllerName() {
        return ptzControllerName;
    }

    public void setPtzControllerName(String ptzControllerName) {
        this.ptzControllerName = ptzControllerName == null ? null : ptzControllerName.trim();
    }

    public String getDeviceResourceType() {
        return deviceResourceType;
    }

    public void setDeviceResourceType(String deviceResourceType) {
        this.deviceResourceType = deviceResourceType == null ? null : deviceResourceType.trim();
    }

    public String getDeviceResourceTypeName() {
        return deviceResourceTypeName;
    }

    public void setDeviceResourceTypeName(String deviceResourceTypeName) {
        this.deviceResourceTypeName = deviceResourceTypeName == null ? null : deviceResourceTypeName.trim();
    }

    public Short getChannelType() {
        return channelType;
    }

    public void setChannelType(Short channelType) {
        this.channelType = channelType;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName == null ? null : channelTypeName.trim();
    }

    public Short getTransType() {
        return transType;
    }

    public void setTransType(Short transType) {
        this.transType = transType;
    }

    public String getTransTypeName() {
        return transTypeName;
    }

    public void setTransTypeName(String transTypeName) {
        this.transTypeName = transTypeName == null ? null : transTypeName.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getUnitIndexCode() {
        return unitIndexCode;
    }

    public void setUnitIndexCode(String unitIndexCode) {
        this.unitIndexCode = unitIndexCode == null ? null : unitIndexCode.trim();
    }

    public String getTreatyType() {
        return treatyType;
    }

    public void setTreatyType(String treatyType) {
        this.treatyType = treatyType == null ? null : treatyType.trim();
    }

    public String getTreatyTypeName() {
        return treatyTypeName;
    }

    public void setTreatyTypeName(String treatyTypeName) {
        this.treatyTypeName = treatyTypeName == null ? null : treatyTypeName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
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
        this.statusName = statusName == null ? null : statusName.trim();
    }

    public String getIsScope() {
        return isScope;
    }

    public void setIsScope(String isScope) {
        this.isScope = isScope == null ? null : isScope.trim();
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

    public String getCrossId() {
        return crossId;
    }

    public void setCrossId(String crossId) {
        this.crossId = crossId;
    }

    public BigInteger getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(BigInteger deviceId) {
        this.deviceId = deviceId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        this.isStatus = isStatus;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public List<MapDevice> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<MapDevice> deviceList) {
        this.deviceList = deviceList;
    }

    public String getPoleId() {
        return poleId;
    }

    public void setPoleId(String poleId) {
        this.poleId = poleId;
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
        this.collLocation = collLocation;
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

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }
}