package com.site.mountain.entity;

import java.util.Date;

public class Map3dtiles extends BaseMapBean{
    private Long tilesId;

    private String tilesName;

    private String tilesType;

    private String tilesVersion;

    private String tilesCode;

    private String uri;

    private Double centerLon;

    private Double centerLat;

    private Long dataId;

    private String thumbnail;

    private Date updateTime;

    private Long updatePerson;


    public Double getCenterLon() {
        return centerLon;
    }

    public void setCenterLon(Double centerLon) {
        this.centerLon = centerLon;
    }

    public Double getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Double centerLat) {
        this.centerLat = centerLat;
    }

    public Long getTilesId() {
        return tilesId;
    }

    public void setTilesId(Long tilesId) {
        this.tilesId = tilesId;
    }

    public String getTilesName() {
        return tilesName;
    }

    public void setTilesName(String tilesName) {
        this.tilesName = tilesName == null ? null : tilesName.trim();
    }

    public String getTilesType() {
        return tilesType;
    }

    public void setTilesType(String tilesType) {
        this.tilesType = tilesType == null ? null : tilesType.trim();
    }

    public String getTilesVersion() {
        return tilesVersion;
    }

    public void setTilesVersion(String tilesVersion) {
        this.tilesVersion = tilesVersion == null ? null : tilesVersion.trim();
    }

    public String getTilesCode() {
        return tilesCode;
    }

    public void setTilesCode(String tilesCode) {
        this.tilesCode = tilesCode == null ? null : tilesCode.trim();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
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