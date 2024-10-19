package com.site.mountain.entity;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtLocationAssetDict<T> extends BaseNtBean{
    public NtLocationAssetDict() {
        poiType = "locatAssetDict";
    }
    public NtLocationAssetDict(Integer pageSize){
        this.pageSize = pageSize;
    }

    private String locatDictId;

    private String name;

    private String tableName;

    private String type;

    private String typeCode;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private List<T> datalist = new ArrayList<>();

    public String getLocatDictId() {
        return locatDictId;
    }

    public void setLocatDictId(String locatDictId) {
        this.locatDictId = locatDictId == null ? null : locatDictId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode == null ? null : typeCode.trim();
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

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }
}