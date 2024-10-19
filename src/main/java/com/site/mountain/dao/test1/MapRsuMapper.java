package com.site.mountain.dao.test1;

import com.site.mountain.entity.MapRsu;

import java.util.List;

public interface MapRsuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapRsu record);

    int insertSelective(MapRsu record);

    MapRsu selectByPrimaryKey(Integer id);

    List<MapRsu> selectAll(MapRsu mapRsu);

    int updateByPrimaryKeySelective(MapRsu record);

    int updateByPrimaryKey(MapRsu record);
}