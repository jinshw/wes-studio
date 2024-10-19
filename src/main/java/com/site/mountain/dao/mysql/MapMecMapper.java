package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapMec;

import java.util.List;

public interface MapMecMapper {
    int deleteByPrimaryKey(Long mecId);

    int insert(MapMec record);

    int insertSelective(MapMec record);

    MapMec selectByPrimaryKey(MapMec mapMec);

    List<MapMec> selectList(MapMec mapMec);

    int updateByPrimaryKeySelective(MapMec record);

    int updateByPrimaryKeyWithBLOBs(MapMec record);

    int updateByPrimaryKey(MapMec record);
}