package com.site.mountain.entity;

public class MapRsu {
    private Integer id;

    private String shebeiId;

    private String changjia;

    private Double lon;

    private Double lat;

    private Double alt;

    private String zhuangtai;

    private String anzhuangTime;

    private String fugaifanwei;

    private String beizhu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShebeiId() {
        return shebeiId;
    }

    public void setShebeiId(String shebeiId) {
        this.shebeiId = shebeiId == null ? null : shebeiId.trim();
    }

    public String getChangjia() {
        return changjia;
    }

    public void setChangjia(String changjia) {
        this.changjia = changjia == null ? null : changjia.trim();
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getAlt() {
        return alt;
    }

    public void setAlt(Double alt) {
        this.alt = alt;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai == null ? null : zhuangtai.trim();
    }

    public String getAnzhuangTime() {
        return anzhuangTime;
    }

    public void setAnzhuangTime(String anzhuangTime) {
        this.anzhuangTime = anzhuangTime == null ? null : anzhuangTime.trim();
    }

    public String getFugaifanwei() {
        return fugaifanwei;
    }

    public void setFugaifanwei(String fugaifanwei) {
        this.fugaifanwei = fugaifanwei == null ? null : fugaifanwei.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }
}