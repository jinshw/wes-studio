package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDeviceType;

import java.util.List;

public interface MapDeviceTypeService {

    int insert(MapDeviceType record);

    MapDeviceType selectByPrimaryKey(Long dtId);

    List<MapDeviceType> selectList(MapDeviceType deviceType);

    List<MapDeviceType> selectRelation(MapDeviceType deviceType);

    PageInfo<MapDeviceType> selectListByPage(MapDeviceType mapDeviceType);

    int updateByPrimaryKeySelective(MapDeviceType record);

    int deleteByPrimaryKey(Long dtId);

}
