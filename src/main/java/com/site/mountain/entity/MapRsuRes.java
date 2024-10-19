package com.site.mountain.entity;

public class MapRsuRes   {

    private Integer id;
    private String shebeiId;
    private String changjia;
    private String zhuangtai;
    private Coordinate coordinate;
    private String anzhuangTime;
    private String fugaifanwei;
    private Double fangwei;
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
        this.shebeiId = shebeiId;
    }

    public String getChangjia() {
        return changjia;
    }

    public void setChangjia(String changjia) {
        this.changjia = changjia;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getAnzhuangTime() {
        return anzhuangTime;
    }

    public void setAnzhuangTime(String anzhuangTime) {
        this.anzhuangTime = anzhuangTime;
    }

    public String getFugaifanwei() {
        return fugaifanwei;
    }

    public void setFugaifanwei(String fugaifanwei) {
        this.fugaifanwei = fugaifanwei;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Double getFangwei() {
        return fangwei;
    }

    public void setFangwei(Double fangwei) {
        this.fangwei = fangwei;
    }
}