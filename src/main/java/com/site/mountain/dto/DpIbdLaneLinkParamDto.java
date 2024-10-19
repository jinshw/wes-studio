package com.site.mountain.dto;

import java.util.Map;

public class DpIbdLaneLinkParamDto {

    public String dataType;

    public String updateType;

    public String id;

    public Map jsonData;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map jsonData) {
        this.jsonData = jsonData;
    }
}
