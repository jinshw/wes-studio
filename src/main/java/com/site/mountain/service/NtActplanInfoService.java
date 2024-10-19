package com.site.mountain.service;

import com.site.mountain.entity.NtActplanInfo;

import java.util.List;

public interface NtActplanInfoService {
    int deleteByPrimaryKey(String actplanId);

    int insert(NtActplanInfo record);

    int insertSelective(NtActplanInfo record);

    NtActplanInfo selectByPrimaryKey(String actplanId);

    int updateByPrimaryKeySelective(NtActplanInfo record);

    int updateByPrimaryKey(NtActplanInfo record);

    List<NtActplanInfo> selectList(NtActplanInfo record);
}
