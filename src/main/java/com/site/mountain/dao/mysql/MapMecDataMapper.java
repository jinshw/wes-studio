package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapMecData;

import java.util.List;

public interface MapMecDataMapper {
    int insert(MapMecData record);

    int insertSelective(MapMecData record);

    List<MapMecData> selectList(MapMecData mapMecData);

    int delete(MapMecData mapMecData);

    int deleteBySectionId(String sectionId);
}