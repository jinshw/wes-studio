package com.site.mountain.service;

import com.site.mountain.entity.NtIbdLaserRadar;

import java.util.List;

public interface NtIbdLaserRadarService {
    int deleteByPrimaryKey(String laserRadarId);

    int insert(NtIbdLaserRadar record);

    int insertSelective(NtIbdLaserRadar record);

    NtIbdLaserRadar selectByPrimaryKey(String laserRadarId);

    int updateByPrimaryKeySelective(NtIbdLaserRadar record);

    int updateByPrimaryKey(NtIbdLaserRadar record);

    List<NtIbdLaserRadar> selectList(NtIbdLaserRadar record);
}
