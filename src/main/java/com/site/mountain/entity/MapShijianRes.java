package com.site.mountain.entity;

public class MapShijianRes implements java.io.Serializable {

    private Integer id;
    private String shijianId;
    private String leixing;
    private String laiyuan;
    private String shijianTime;
    private Coordinate coordinate;
    private String chedao;
    private String kexindu;
    private String fugaibanjing;
    private String beizhu;

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
        this.shijianId = shijianId;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getLaiyuan() {
        return laiyuan;
    }

    public void setLaiyuan(String laiyuan) {
        this.laiyuan = laiyuan;
    }

    public String getShijianTime() {
        return shijianTime;
    }

    public void setShijianTime(String shijianTime) {
        this.shijianTime = shijianTime;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getChedao() {
        return chedao;
    }

    public void setChedao(String chedao) {
        this.chedao = chedao;
    }

    public String getKexindu() {
        return kexindu;
    }

    public void setKexindu(String kexindu) {
        this.kexindu = kexindu;
    }

    public String getFugaibanjing() {
        return fugaibanjing;
    }

    public void setFugaibanjing(String fugaibanjing) {
        this.fugaibanjing = fugaibanjing;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
