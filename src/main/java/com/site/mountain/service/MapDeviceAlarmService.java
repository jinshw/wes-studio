package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDeviceAlarm;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MapDeviceAlarmService {
    PageInfo<MapDeviceAlarm> selectListByPage(MapDeviceAlarm mapDeviceAlarm);

    ResponseEntity export(MapDeviceAlarm mapDeviceAlarm);

    int deleteByPrimaryKey(String mdaId);

    int insert(MapDeviceAlarm record);

    int insertSelective(MapDeviceAlarm record);

    MapDeviceAlarm selectByPrimaryKey(String mdaId);

    List<MapDeviceAlarm> selectList(MapDeviceAlarm mapDeviceAlarm);

    List<Map<String, Object>> selectListMap(MapDeviceAlarm mapDeviceAlarm);
}
