package com.site.mountain.entity;

public class NtIbdAssetSectionDirection extends BaseNtBean{
    public NtIbdAssetSectionDirection() {
        poiType = "sectionDirection";
    }
    private String directionId;

    private String name;

    private String directionCode;

    private String directionType;

    private String polygon;

    private String polygonCenter;

    private String lineColor;

    private String lineWidth;

    private String lineStyle;

    private String outlineColor;

    private String outlineWidth;

    private String outlineStyle;

    private String sectionId;

    public String getDirectionId() {
        return directionId;
    }

    public void setDirectionId(String directionId) {
        this.directionId = directionId == null ? null : directionId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDirectionCode() {
        return directionCode;
    }

    public void setDirectionCode(String directionCode) {
        this.directionCode = directionCode == null ? null : directionCode.trim();
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType == null ? null : directionType.trim();
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon == null ? null : polygon.trim();
    }

    public String getPolygonCenter() {
        return polygonCenter;
    }

    public void setPolygonCenter(String polygonCenter) {
        this.polygonCenter = polygonCenter == null ? null : polygonCenter.trim();
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor == null ? null : lineColor.trim();
    }

    public String getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(String lineWidth) {
        this.lineWidth = lineWidth == null ? null : lineWidth.trim();
    }

    public String getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(String lineStyle) {
        this.lineStyle = lineStyle == null ? null : lineStyle.trim();
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor == null ? null : outlineColor.trim();
    }

    public String getOutlineWidth() {
        return outlineWidth;
    }

    public void setOutlineWidth(String outlineWidth) {
        this.outlineWidth = outlineWidth == null ? null : outlineWidth.trim();
    }

    public String getOutlineStyle() {
        return outlineStyle;
    }

    public void setOutlineStyle(String outlineStyle) {
        this.outlineStyle = outlineStyle == null ? null : outlineStyle.trim();
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId == null ? null : sectionId.trim();
    }
}