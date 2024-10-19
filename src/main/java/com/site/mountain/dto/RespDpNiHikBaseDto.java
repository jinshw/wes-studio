package com.site.mountain.dto;

import org.apache.poi.ss.formula.functions.T;

/**
 * 返回大屏-视频显示对象
 */
public class RespDpNiHikBaseDto<T>{
    private String id;
    private String type;
    private String iconUrl;
    private String textColor;
//    private Double[] points;
    private T points;

    private T propertyInfo;

    private T subPoints;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public T getPoints() {
        return points;
    }

    public void setPoints(T points) {
        this.points = points;
    }

    public T getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(T propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

    public T getSubPoints() {
        return subPoints;
    }

    public void setSubPoints(T subPoints) {
        this.subPoints = subPoints;
    }
}
