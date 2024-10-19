package com.site.mountain.entity;

import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;
import java.math.BigInteger;


public class CmsContent {
    private String id;
    private String title;
    private String shortTitle;
    private String oldTitle;
    private String author;
    private String editor;
    private String columnId;
    private String logo;
    private String content;
    private String source;
    private Integer status;
    private Integer orders;
    private Integer isTop;
    private String remark;
    private Timestamp publishTime;
    private Timestamp saveTime;
    private BigInteger optPerson;

    // 扩展字段
    private Integer pageSize;// 一页多少条记录
    private Integer pageNo;// 当前页

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getOldTitle() {
        return oldTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getEditor() {
        return editor;
    }

    public String getColumnId() {
        return columnId;
    }

    public String getLogo() {
        return logo;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getOrders() {
        return orders;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public String getRemark() {
        return remark;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public Timestamp getSaveTime() {
        return saveTime;
    }

    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public void setSaveTime(Timestamp saveTime) {
        this.saveTime = saveTime;
    }

    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }


}
