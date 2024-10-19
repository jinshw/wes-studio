package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapDeviceType;

import java.util.List;

public interface MapDeviceTypeMapper {
    int deleteByPrimaryKey(Long dtId);

    int insert(MapDeviceType record);

    int insertSelective(MapDeviceType record);

    MapDeviceType selectByPrimaryKey(Long dtId);

    List<MapDeviceType> selectList(MapDeviceType deviceType);
    List<MapDeviceType> selectRelation(MapDeviceType deviceType);

    int updateByPrimaryKeySelective(MapDeviceType record);

    int updateByPrimaryKey(MapDeviceType record);
}