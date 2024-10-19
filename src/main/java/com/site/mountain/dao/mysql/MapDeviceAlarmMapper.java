package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapDeviceAlarm;

import java.util.List;
import java.util.Map;

public interface MapDeviceAlarmMapper {
    int deleteByPrimaryKey(String mdaId);

    int insert(MapDeviceAlarm record);

    int insertSelective(MapDeviceAlarm record);

    MapDeviceAlarm selectByPrimaryKey(String mdaId);

    List<MapDeviceAlarm> selectList(MapDeviceAlarm mapDeviceAlarm);

    List<Map<String, Object>> selectListMap(MapDeviceAlarm mapDeviceAlarm);

    int updateByPrimaryKeySelective(MapDeviceAlarm record);

    int updateByPrimaryKey(MapDeviceAlarm record);
}