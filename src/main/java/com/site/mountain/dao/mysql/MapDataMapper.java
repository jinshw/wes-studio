package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.entity.MapSectionData;

import java.util.List;
import java.util.Map;

public interface MapDataMapper {
    int deleteByPrimaryKey(Long dataId);

    int insert(MapData record);

    int insertSelective(MapData record);

    MapData selectByPrimaryKey(Long dataId);

    List<MapData> selectAll(MapData mapData);

    List<MapSectionData> selectMecDataList(MapMecData mapMecData);

    int updateByPrimaryKeySelective(MapData record);

    int updateByPrimaryKey(MapData record);

    List<Map<String, Object>> selectRelation(Long sectionId);
}