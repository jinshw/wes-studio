package com.site.mountain.service;

import com.site.mountain.entity.NtTrffassetLaserRadar;

import java.util.List;

public interface NtTrffassetLaserRadarService {
    int deleteByPrimaryKey(String id);

    int insert(NtTrffassetLaserRadar record);

    int insertSelective(NtTrffassetLaserRadar record);

    NtTrffassetLaserRadar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(NtTrffassetLaserRadar record);

    int updateByPrimaryKey(NtTrffassetLaserRadar record);

    List<NtTrffassetLaserRadar> selectList(NtTrffassetLaserRadar record);
}
