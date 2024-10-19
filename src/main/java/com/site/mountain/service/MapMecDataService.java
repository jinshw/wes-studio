package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapMecData;

import java.util.List;

public interface MapMecDataService {

    int insertSelective(MapMecData record);

    PageInfo<MapMecData> selectList(MapMecData mapMecData);

    int delete(MapMecData mapMecData);

    int deleteBySectionId(String sectionId);

}
