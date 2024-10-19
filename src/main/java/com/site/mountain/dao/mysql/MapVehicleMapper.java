package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapVehicle;

import java.util.List;
import java.util.Map;

public interface MapVehicleMapper {
    int deleteByPrimaryKey(Long vehicleId);

    int insert(MapVehicle record);

    int insertSelective(MapVehicle record);

    MapVehicle selectByPrimaryKey(Long vehicleId);

    List<MapVehicle> selectList(MapVehicle mapVehicle);

    List<Map<String,Object>> selectListMap(MapVehicle mapVehicle);

    int updateByPrimaryKeySelective(MapVehicle record);

    int updateByPrimaryKey(MapVehicle record);
}