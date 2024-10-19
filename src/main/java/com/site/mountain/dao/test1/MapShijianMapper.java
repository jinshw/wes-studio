package com.site.mountain.dao.test1;

import com.site.mountain.entity.MapShijian;

import java.util.List;

public interface MapShijianMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapShijian record);

    int insertSelective(MapShijian record);

    MapShijian selectByPrimaryKey(Integer id);

    List<MapShijian> selectAll(MapShijian mapShijian);

    int updateByPrimaryKeySelective(MapShijian record);

    int updateByPrimaryKey(MapShijian record);
}