package com.site.mountain.entity;

import java.util.Date;

public class MapService {
    private Long msId;

    private String msType;

    private String msUrl;

    private String msVersion;

    private Integer status;

    private Long dataId;

    private Date updateTime;

    private Long updatePerson;

    public Long getMsId() {
        return msId;
    }

    public void setMsId(Long msId) {
        this.msId = msId;
    }

    public String getMsType() {
        return msType;
    }

    public void setMsType(String msType) {
        this.msType = msType == null ? null : msType.trim();
    }

    public String getMsUrl() {
        return msUrl;
    }

    public void setMsUrl(String msUrl) {
        this.msUrl = msUrl == null ? null : msUrl.trim();
    }

    public String getMsVersion() {
        return msVersion;
    }

    public void setMsVersion(String msVersion) {
        this.msVersion = msVersion == null ? null : msVersion.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
    }
}