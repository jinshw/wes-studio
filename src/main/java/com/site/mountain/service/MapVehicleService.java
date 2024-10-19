package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapVehicle;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MapVehicleService {

    PageInfo<MapVehicle> selectListByPage(MapVehicle mapVehicle);

    ResponseEntity export(MapVehicle mapVehicle);


    int deleteByPrimaryKey(Long vehicleId);

    int insert(MapVehicle record);

    int insertSelective(MapVehicle record);

    MapVehicle selectByPrimaryKey(Long vehicleId);

    List<MapVehicle> selectList(MapVehicle mapVehicle);

    int updateByPrimaryKeySelective(MapVehicle record);

    int updateByPrimaryKey(MapVehicle record);

    int deleteList(List<MapVehicle> list);
}
