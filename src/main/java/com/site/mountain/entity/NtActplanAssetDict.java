package com.site.mountain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NtActplanAssetDict extends BaseNtBean{
    public NtActplanAssetDict(){
        poiType = "actplanAssetDict";
    }

    private String assetDictId;

    private String name;

    private String englishName;

    private String type;

    private String typeCode;

    private String entityType;

    private String entityTypeCode;

    private Boolean checked;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private List layerArr = new ArrayList();

    private List positionData = new ArrayList();

    private List dictTypeList = new ArrayList();

    public String getAssetDictId() {
        return assetDictId;
    }

    public void setAssetDictId(String assetDictId) {
        this.assetDictId = assetDictId == null ? null : assetDictId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType == null ? null : entityType.trim();
    }

    public String getEntityTypeCode() {
        return entityTypeCode;
    }

    public void setEntityTypeCode(String entityTypeCode) {
        this.entityTypeCode = entityTypeCode == null ? null : entityTypeCode.trim();
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
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

    public List getLayerArr() {
        return layerArr;
    }

    public void setLayerArr(List layerArr) {
        this.layerArr = layerArr;
    }

    public List getPositionData() {
        return positionData;
    }

    public void setPositionData(List positionData) {
        this.positionData = positionData;
    }

    public List getDictTypeList() {
        return dictTypeList;
    }

    public void setDictTypeList(List dictTypeList) {
        this.dictTypeList = dictTypeList;
    }
}