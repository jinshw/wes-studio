package com.site.mountain.entity;

import java.util.Date;

public class MapDeviceAlarm extends BaseMapBean{
    private String mdaId;

    private String deviceCode;

    private String deviceType;

    private String alarmContent;

    private String deviceStatus;

    private Date createTime;

    public String getMdaId() {
        return mdaId;
    }

    public void setMdaId(String mdaId) {
        this.mdaId = mdaId == null ? null : mdaId.trim();
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode == null ? null : deviceCode.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent == null ? null : alarmContent.trim();
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus == null ? null : deviceStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}