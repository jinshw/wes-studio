package com.site.mountain.entity;

import java.sql.Timestamp;
import java.util.Date;

public class MapAccess {
    private Long id;

    private String mapdataCode;

    private String accessAccount;

    private Long accessPerson;

    private Long accessNum;

    private Timestamp optTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMapdataId() {
        return mapdataCode;
    }

    public void setMapdataCode(String mapdataCode) {
        this.mapdataCode = mapdataCode;
    }

    public String getAccessAccount() {
        return accessAccount;
    }

    public void setAccessAccount(String accessAccount) {
        this.accessAccount = accessAccount == null ? null : accessAccount.trim();
    }

    public Long getAccessPerson() {
        return accessPerson;
    }

    public void setAccessPerson(Long accessPerson) {
        this.accessPerson = accessPerson;
    }

    public Long getAccessNum() {
        return accessNum;
    }

    public void setAccessNum(Long accessNum) {
        this.accessNum = accessNum;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }
}