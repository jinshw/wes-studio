package com.site.mountain.service;

import com.site.mountain.entity.MapSectionData;

public interface MapSectionDataService {
    int insertSelective(MapSectionData record);

    int deleteBySectionId(String sectionId);
}
