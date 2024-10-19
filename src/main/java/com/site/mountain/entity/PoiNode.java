package com.site.mountain.entity;

public class PoiNode {
    private String key;
    private String title;
    private Integer columnWidth;
    private Boolean isDate = false;

    public PoiNode() {
    }

    public PoiNode(String key, String title) {
        this.key = key;
        this.title = title;
        this.columnWidth = 10;
    }

    public PoiNode(String key, String title, Integer columnWidth) {
        this.key = key;
        this.title = title;
        this.columnWidth = columnWidth;
    }

    public PoiNode(String key, String title, Integer columnWidth, Boolean isDate) {
        this.key = key;
        this.title = title;
        this.columnWidth = columnWidth;
        this.isDate = isDate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    @Override
    public String toString() {
        return "PoiNode{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", columnWidth=" + columnWidth +
                '}';
    }
}
