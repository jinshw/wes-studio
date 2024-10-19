package com.site.mountain.entity;

import java.util.List;

/**
 * 地图基础Bean类
 */
public class BaseMapBean {

    // 当前页数
    public Integer pageNum = 1;
    // 每页条数
    public Integer pageSize = 10;
    // 距离（米）
    public Double distance = 0.0;
    // 编码
    public String code;
    // 文件内容：例如geojson
    public String content;

    public String fileUrl = "";

    public String account = "";

    public List<SysUser> userList = null;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }


    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
