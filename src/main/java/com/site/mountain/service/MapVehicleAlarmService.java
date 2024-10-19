package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.MapVehicleAlarm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MapVehicleAlarmService {

    List<MapVehicleAlarm> selectList(MapVehicleAlarm mapVehicleAlarm);


    PageInfo<MapVehicleAlarm> selectListByPage(MapVehicleAlarm mapVehicleAlarm);

    ResponseEntity export(MapVehicleAlarm mapVehicleAlarm);
}
