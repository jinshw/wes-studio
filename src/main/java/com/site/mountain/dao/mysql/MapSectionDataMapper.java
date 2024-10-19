package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapSectionData;

public interface MapSectionDataMapper {
    int deleteByPrimaryKey(String sectionId);

    int insert(MapSectionData record);

    int insertSelective(MapSectionData record);

    MapSectionData selectByPrimaryKey(String sectionId);

    int updateByPrimaryKeySelective(MapSectionData record);

    int updateByPrimaryKey(MapSectionData record);
}