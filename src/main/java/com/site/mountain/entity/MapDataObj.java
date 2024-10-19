package com.site.mountain.entity;

import java.util.Date;

public class MapDataObj extends BaseMapBean{
    private Long mdobjId;

    private String sheetNumber;

    private String x;

    private String y;

    private String z;

    private Long dataId;

    private Date updateTime;

    private Long updatePerson;

    public Long getMdobjId() {
        return mdobjId;
    }

    public void setMdobjId(Long mdobjId) {
        this.mdobjId = mdobjId;
    }

    public String getSheetNumber() {
        return sheetNumber;
    }

    public void setSheetNumber(String sheetNumber) {
        this.sheetNumber = sheetNumber == null ? null : sheetNumber.trim();
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x == null ? null : x.trim();
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y == null ? null : y.trim();
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z == null ? null : z.trim();
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