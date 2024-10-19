package com.site.mountain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppAccident {
    private Integer id;

    private String sgbh;

    private Date sgfssj;

    private String sf;

    private String ds;

    private Integer swrs;

    private Integer ssrs;

    private String sgdd;

    private String rdyy;

    private String sgxt;

    private String cljsg;

    private String sgzr;

    private String shcd;

    private String hphm;

    private String clpp;

    private String vin;

    private String cllx;

    private Boolean isActive;

    private String clxh;

    private String xszt;

    private String jyaq;

    // 扩展字段
    private String startDateStr;
    private String endDateStr;
    private String yearMonth;

    private String name;
    private String value;

    private BigDecimal jd;

    private BigDecimal wd;

    private Map<String, List> sgfbMap = new HashMap<>();

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Map<String, List> getSgfbMap() {
        return sgfbMap;
    }

    public void setSgfbMap(Map<String, List> sgfbMap) {
        this.sgfbMap = sgfbMap;
    }

    public BigDecimal getJd() {
        return jd;
    }

    public void setJd(BigDecimal jd) {
        this.jd = jd;
    }

    public BigDecimal getWd() {
        return wd;
    }

    public void setWd(BigDecimal wd) {
        this.wd = wd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSgbh() {
        return sgbh;
    }

    public void setSgbh(String sgbh) {
        this.sgbh = sgbh == null ? null : sgbh.trim();
    }

    public Date getSgfssj() {
        return sgfssj;
    }

    public void setSgfssj(Date sgfssj) {
        this.sgfssj = sgfssj;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf == null ? null : sf.trim();
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds == null ? null : ds.trim();
    }

    public Integer getSwrs() {
        return swrs;
    }

    public void setSwrs(Integer swrs) {
        this.swrs = swrs;
    }

    public Integer getSsrs() {
        return ssrs;
    }

    public void setSsrs(Integer ssrs) {
        this.ssrs = ssrs;
    }

    public String getSgdd() {
        return sgdd;
    }

    public void setSgdd(String sgdd) {
        this.sgdd = sgdd == null ? null : sgdd.trim();
    }

    public String getRdyy() {
        return rdyy;
    }

    public void setRdyy(String rdyy) {
        this.rdyy = rdyy == null ? null : rdyy.trim();
    }

    public String getSgxt() {
        return sgxt;
    }

    public void setSgxt(String sgxt) {
        this.sgxt = sgxt == null ? null : sgxt.trim();
    }

    public String getCljsg() {
        return cljsg;
    }

    public void setCljsg(String cljsg) {
        this.cljsg = cljsg == null ? null : cljsg.trim();
    }

    public String getSgzr() {
        return sgzr;
    }

    public void setSgzr(String sgzr) {
        this.sgzr = sgzr == null ? null : sgzr.trim();
    }

    public String getShcd() {
        return shcd;
    }

    public void setShcd(String shcd) {
        this.shcd = shcd == null ? null : shcd.trim();
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm == null ? null : hphm.trim();
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp == null ? null : clpp.trim();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin == null ? null : vin.trim();
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx == null ? null : cllx.trim();
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getClxh() {
        return clxh;
    }

    public void setClxh(String clxh) {
        this.clxh = clxh == null ? null : clxh.trim();
    }

    public String getXszt() {
        return xszt;
    }

    public void setXszt(String xszt) {
        this.xszt = xszt == null ? null : xszt.trim();
    }

    public String getJyaq() {
        return jyaq;
    }

    public void setJyaq(String jyaq) {
        this.jyaq = jyaq == null ? null : jyaq.trim();
    }
}