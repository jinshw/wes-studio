package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapDataObj;

import java.util.List;

public interface MapDataObjMapper {
    int deleteByPrimaryKey(Long mdobjId);

    int insert(MapDataObj record);

    int insertSelective(MapDataObj record);

    MapDataObj selectByPrimaryKey(Long mdobjId);

    List<MapDataObj> selectAll(MapDataObj mapDataObj);

    int updateByPrimaryKeySelective(MapDataObj record);

    int updateByPrimaryKey(MapDataObj record);
}