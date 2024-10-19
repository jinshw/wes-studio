package com.site.mountain.dto;

public class DpAssetParamDto {

    private String poiType;

    private String id;

    private String directionType;

    private String typeCode;

    private String[] typeCodes;

    public String getPoiType() {
        return poiType;
    }

    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String[] getTypeCodes() {
        return typeCodes;
    }

    public void setTypeCodes(String[] typeCodes) {
        this.typeCodes = typeCodes;
    }
}
