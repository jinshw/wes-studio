package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapVehicleAlarm;

import java.util.List;
import java.util.Map;

public interface MapVehicleAlarmMapper {
    int deleteByPrimaryKey(Long vaId);

    int insert(MapVehicleAlarm record);

    int insertSelective(MapVehicleAlarm record);

    MapVehicleAlarm selectByPrimaryKey(Long vaId);

    List<MapVehicleAlarm> selectList(MapVehicleAlarm mapVehicleAlarm);

    List<Map<String,Object>> selectListMap(MapVehicleAlarm mapVehicleAlarm);

    int updateByPrimaryKeySelective(MapVehicleAlarm record);

    int updateByPrimaryKey(MapVehicleAlarm record);
}