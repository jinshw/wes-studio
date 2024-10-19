package com.site.mountain.entity;

public class MapShijian {
    private Integer id;

    private String shijianId;

    private String leixing;

    private String laiyuan;

    private Double lon;

    private Double lat;

    private Double alt;

    private String chedao;

    private String kexindu;

    private String fugaibanjing;

    private String beizhu;

    private String shijianTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShijianId() {
        return shijianId;
    }

    public void setShijianId(String shijianId) {
        this.shijianId = shijianId == null ? null : shijianId.trim();
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing == null ? null : leixing.trim();
    }

    public String getLaiyuan() {
        return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan == null ? null : laiyuan.trim();
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

    public String getChedao() {
        return chedao;
    }

    public void setChedao(String chedao) {
        this.chedao = chedao == null ? null : chedao.trim();
    }

    public String getKexindu() {
        return kexindu;
    }

    public void setKexindu(String kexindu) {
        this.kexindu = kexindu == null ? null : kexindu.trim();
    }

    public String getFugaibanjing() {
        return fugaibanjing;
    }

    public void setFugaibanjing(String fugaibanjing) {
        this.fugaibanjing = fugaibanjing == null ? null : fugaibanjing.trim();
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public String getShijianTime() {
        return shijianTime;
    }

    public void setShijianTime(String shijianTime) {
        this.shijianTime = shijianTime == null ? null : shijianTime.trim();
    }
}