package com.site.mountain.entity;

import java.util.Date;
import java.util.List;

public class MapData extends BaseMapBean {
    private Long dataId;

    private String dataName;

    private String version;

    // 状态：1未发布、2已经发布、3 已经废除
    private Integer status;

    // 数据类型：例如：geojson,obj,gltf,shape
    private String dataType;

    private String path;

    private String mainFile;

    private String mongoId;

    private String code;

    private Date updateTime;

    private Long updatePerson;

    private List<MapMec> mapMecList;

    private List<MapDevice> mapDeviceList;

    // 状态：1未下发，2已经下发
    private Integer mecDataStatus;

    private Date optTime;

    private Long optPerson;

    private String objPath;
    private String tilesPath;

    private Double objLon;
    private Double objLat;
    private Double objAlt;

    public Double getObjLon() {
        return objLon;
    }

    public void setObjLon(Double objLon) {
        this.objLon = objLon;
    }

    public Double getObjLat() {
        return objLat;
    }

    public void setObjLat(Double objLat) {
        this.objLat = objLat;
    }

    public Double getObjAlt() {
        return objAlt;
    }

    public void setObjAlt(Double objAlt) {
        this.objAlt = objAlt;
    }

    public String getObjPath() {
        return objPath;
    }

    public void setObjPath(String objPath) {
        this.objPath = objPath;
    }

    public String getTilesPath() {
        return tilesPath;
    }

    public void setTilesPath(String tilesPath) {
        this.tilesPath = tilesPath;
    }

    public List<MapDevice> getMapDeviceList() {
        return mapDeviceList;
    }

    public void setMapDeviceList(List<MapDevice> mapDeviceList) {
        this.mapDeviceList = mapDeviceList;
    }

    public Integer getMecDataStatus() {
        return mecDataStatus;
    }

    public void setMecDataStatus(Integer mecDataStatus) {
        this.mecDataStatus = mecDataStatus;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public Long getOptPerson() {
        return optPerson;
    }

    public void setOptPerson(Long optPerson) {
        this.optPerson = optPerson;
    }

    public List<MapMec> getMapMecList() {
        return mapMecList;
    }

    public void setMapMecList(List<MapMec> mapMecList) {
        this.mapMecList = mapMecList;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getMainFile() {
        return mainFile;
    }

    public void setMainFile(String mainFile) {
        this.mainFile = mainFile == null ? null : mainFile.trim();
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId == null ? null : mongoId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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