package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.List;

public class BaseNtBean {
    public String poiType = "ntIbdData";

    public Integer pageNum = 1;

    public Integer pageSize = 10;

    private String id;

    private String deviceCode;

    private String deviceTypeCode;

    private String sectionName;

    private String crossName;

    private String typeName;

    private String typeCode;

    private List<String> deviceTypeCodeList;

    private List<ProjectFile> imgList= new ArrayList<>();

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getCrossName() {
        return crossName;
    }

    public void setCrossName(String crossName) {
        this.crossName = crossName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    public void setDeviceTypeCode(String deviceTypeCode) {
        this.deviceTypeCode = deviceTypeCode;
    }

    public List<String> getDeviceTypeCodeList() {
        return deviceTypeCodeList;
    }

    public void setDeviceTypeCodeList(List<String> deviceTypeCodeList) {
        this.deviceTypeCodeList = deviceTypeCodeList;
    }

    public List<ProjectFile> getImgList() {
        return imgList;
    }

    public void setImgList(List<ProjectFile> imgList) {
        this.imgList = imgList;
    }
}
