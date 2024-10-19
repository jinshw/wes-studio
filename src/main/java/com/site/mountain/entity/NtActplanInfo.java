package com.site.mountain.entity;

import java.util.Date;

public class NtActplanInfo extends BaseNtBean{

    public NtActplanInfo(){
        poiType = "actplanInfo";
    }

    private String actplanId;

    private String name;

    private String actplanCode;

    private String assetDictGroup;

    private Integer num;

    private String isDel;

    private Long optPerson;

    private Date optTime;

    private Date createTime;

    private String desc;

    private Boolean checked;

    public String getActplanId() {
        return actplanId;
    }

    public void setActplanId(String actplanId) {
        this.actplanId = actplanId == null ? null : actplanId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getActplanCode() {
        return actplanCode;
    }

    public void setActplanCode(String actplanCode) {
        this.actplanCode = actplanCode == null ? null : actplanCode.trim();
    }

    public String getAssetDictGroup() {
        return assetDictGroup;
    }

    public void setAssetDictGroup(String assetDictGroup) {
        this.assetDictGroup = assetDictGroup == null ? null : assetDictGroup.trim();
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

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}