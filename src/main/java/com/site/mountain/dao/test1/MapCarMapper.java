package com.site.mountain.dao.test1;

import com.site.mountain.entity.MapCar;

import java.util.List;

public interface MapCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapCar record);

    int insertSelective(MapCar record);

    MapCar selectByPrimaryKey(Integer id);

    List<MapCar> selectAll(MapCar mapCar);

    List<MapCar> findAllShuliang(MapCar mapCar);

    List<MapCar> findZaixianShuliang(MapCar mapCar);

    int updateByPrimaryKeySelective(MapCar record);

    int updateByPrimaryKey(MapCar record);
}