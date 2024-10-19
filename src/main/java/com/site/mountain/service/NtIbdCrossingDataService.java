package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.NtIbdCrossingData;

import java.util.List;

public interface NtIbdCrossingDataService {

    int deleteByPrimaryKey(String crossId);

    int insert(NtIbdCrossingData record);

    int insertSelective(NtIbdCrossingData record);

    NtIbdCrossingData selectByPrimaryKey(String crossId);

    int updateByPrimaryKeySelective(NtIbdCrossingData record);

    int updateByPrimaryKey(NtIbdCrossingData record);

    List<NtIbdCrossingData> selectList(NtIbdCrossingData record);

    PageInfo<NtIbdCrossingData> selectListByPage(NtIbdCrossingData record);

    int updateIsDelByPrimaryKey(String crossId);
}
