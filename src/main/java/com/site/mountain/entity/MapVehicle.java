package com.site.mountain.entity;

import java.sql.Timestamp;
import java.util.Date;

public class MapVehicle extends BaseMapBean {
    private Long vehicleId;

    private String vehicleNumber = "";

    private String vin = "";

    private String brand = "";

    private String model;

    private String vehicleType = "";

    private String enterprises;

    private String drivingLevel;

    private Integer hasObu;

    private String obuCode;

    private String driverName;

    private String phone;

    private String status = "";

    private String vehicleImg;

    private String isDel;

    private Long optPerson;

    private Timestamp optTime;

    private Timestamp createTime;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber == null ? null : vehicleNumber.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType == null ? null : vehicleType.trim();
    }

    public String getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(String enterprises) {
        this.enterprises = enterprises == null ? null : enterprises.trim();
    }

    public String getDrivingLevel() {
        return drivingLevel;
    }

    public void setDrivingLevel(String drivingLevel) {
        this.drivingLevel = drivingLevel == null ? null : drivingLevel.trim();
    }

    public Integer getHasObu() {
        return hasObu;
    }

    public void setHasObu(Integer hasObu) {
        this.hasObu = hasObu;
    }

    public String getObuCode() {
        return obuCode;
    }

    public void setObuCode(String obuCode) {
        this.obuCode = obuCode == null ? null : obuCode.trim();
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName == null ? null : driverName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getVehicleImg() {
        return vehicleImg;
    }

    public void setVehicleImg(String vehicleImg) {
        this.vehicleImg = vehicleImg == null ? null : vehicleImg.trim();
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

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}