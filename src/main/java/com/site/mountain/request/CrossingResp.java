package com.site.mountain.request;

import com.site.mountain.entity.NtHikCrossing;

import java.util.List;

public class CrossingResp {

    private Integer total;

    private Integer pageNo;

    private Integer pageSize;

    private List<NtHikCrossing> list;

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

    public List<NtHikCrossing> getList() {
        return list;
    }

    public void setList(List<NtHikCrossing> list) {
        this.list = list;
    }
}
