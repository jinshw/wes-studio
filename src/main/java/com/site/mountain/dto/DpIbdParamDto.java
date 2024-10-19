package com.site.mountain.dto;

import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

public class DpIbdParamDto {

    public String objType;

    public String opterateType;

    public Map jsonData;

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getOpterateType() {
        return opterateType;
    }

    public void setOpterateType(String opterateType) {
        this.opterateType = opterateType;
    }

    public Map getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map jsonData) {
        this.jsonData = jsonData;
    }
}
