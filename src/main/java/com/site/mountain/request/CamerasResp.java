package com.site.mountain.request;

import com.site.mountain.entity.NtHikCameras;

import java.util.List;

public class CamerasResp {

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    private List<NtHikCameras> list;


    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

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

    public List<NtHikCameras> getList() {
        return list;
    }

    public void setList(List<NtHikCameras> list) {
        this.list = list;
    }
}
