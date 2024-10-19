package com.site.mountain.dao.mysql;

import com.site.mountain.entity.NtIbdCrossingData;

import java.util.List;

public interface NtIbdCrossingDataMapper {
    int deleteByPrimaryKey(String crossId);

    int insert(NtIbdCrossingData record);

    int insertSelective(NtIbdCrossingData record);

    NtIbdCrossingData selectByPrimaryKey(String crossId);

    int updateByPrimaryKeySelective(NtIbdCrossingData record);

    int updateByPrimaryKey(NtIbdCrossingData record);

    List<NtIbdCrossingData> selectList(NtIbdCrossingData record);

    int updateIsDelByPrimaryKey(String crossId);
}