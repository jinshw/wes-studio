package com.site.mountain.dao.test1;

import com.site.mountain.entity.MapCarSpeed;

import java.util.List;

public interface MapCarSpeedMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapCarSpeed record);

    int insertSelective(MapCarSpeed record);

    MapCarSpeed selectByPrimaryKey(Integer id);

    List<MapCarSpeed> findAllCarSpeed(MapCarSpeed mapCarSpeed);

    List<MapCarSpeed> findAllCarNow(MapCarSpeed mapCarSpeed);

    int updateByPrimaryKeySelective(MapCarSpeed record);

    int updateByPrimaryKey(MapCarSpeed record);
}