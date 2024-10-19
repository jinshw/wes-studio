package com.site.mountain.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class MapDevice extends BaseMapBean {
    private Long id;

    private String deviceCode = "";

    private Long deviceType;

    private String brand;

    private String deviceModel;

    private String serialNumber;

    private String poleCode;

    private String orientation;

    private Long connectMec;

    private String roadLoction;

    private Double lon;

    private Double lat;

    private Double alt;

    private Double pitch;

    private Double roll;

    private Double heading;

    private String ip;

    private String mesh;

    private String mask;

    private String gateway;

    private String serverConnect;

    private String status = "";

    private Double effectiveRadius;

    private String playAddress;

    private String isDel;

    private Long optPerson;

    private Timestamp optTime;

    private Timestamp createTime;

    private String objPath;
    private String gltfPath;
    private String tilesetPath;

    private String deviceTypeCode;

    private List<String> deviceTypeCodeList;

    private String cameraProperty;

    public String getObjPath() {
        return objPath;
    }

    public void setObjPath(String objPath) {
        this.objPath = objPath;
    }

    public String getGltfPath() {
        return gltfPath;
    }

    public void setGltfPath(String gltfPath) {
        this.gltfPath = gltfPath;
    }

    public String getTilesetPath() {
        return tilesetPath;
    }

    public void setTilesetPath(String tilesetPath) {
        this.tilesetPath = tilesetPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getPoleCode() {
        return poleCode;
    }

    public void setPoleCode(String poleCode) {
        this.poleCode = poleCode == null ? null : poleCode.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    public Long getConnectMec() {
        return connectMec;
    }

    public void setConnectMec(Long connectMec) {
        this.connectMec = connectMec;
    }

    public String getRoadLoction() {
        return roadLoction;
    }

    public void setRoadLoction(String roadLoction) {
        this.roadLoction = roadLoction == null ? null : roadLoction.trim();
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMesh() {
        return mesh;
    }

    public void setMesh(String mesh) {
        this.mesh = mesh == null ? null : mesh.trim();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway == null ? null : gateway.trim();
    }

    public String getServerConnect() {
        return serverConnect;
    }

    public void setServerConnect(String serverConnect) {
        this.serverConnect = serverConnect == null ? null : serverConnect.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Double getEffectiveRadius() {
        return effectiveRadius;
    }

    public void setEffectiveRadius(Double effectiveRadius) {
        this.effectiveRadius = effectiveRadius;
    }

    public String getPlayAddress() {
        return playAddress;
    }

    public void setPlayAddress(String playAddress) {
        this.playAddress = playAddress == null ? null : playAddress.trim();
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

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public String getCameraProperty() {
        return cameraProperty;
    }

    public void setCameraProperty(String cameraProperty) {
        this.cameraProperty = cameraProperty;
    }

    public List<String> getDeviceTypeCodeList() {
        return deviceTypeCodeList;
    }

    public void setDeviceTypeCodeList(List<String> deviceTypeCodeList) {
        this.deviceTypeCodeList = deviceTypeCodeList;
    }
}