package com.site.mountain.entity;

public class NtAreaFence {
    private Integer id;

    private String name;

    private String polygons;

    private String type;

    private String touch;

    private Integer isDelete;

    private Integer status;

    private String filledColor;

    private String actplanId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPolygons() {
        return polygons;
    }

    public void setPolygons(String polygons) {
        this.polygons = polygons == null ? null : polygons.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTouch() {
        return touch;
    }

    public void setTouch(String touch) {
        this.touch = touch == null ? null : touch.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFilledColor() {
        return filledColor;
    }

    public void setFilledColor(String filledColor) {
        this.filledColor = filledColor == null ? null : filledColor.trim();
    }

    public String getActplanId() {
        return actplanId;
    }

    public void setActplanId(String actplanId) {
        this.actplanId = actplanId;
    }
}