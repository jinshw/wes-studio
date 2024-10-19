package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.MapSectionDataMapper;
import com.site.mountain.entity.MapSectionData;
import com.site.mountain.service.MapSectionDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapSectionDataServiceImpl implements MapSectionDataService {

    @Autowired
    MapSectionDataMapper mapSectionDataMapper;

    @Override
    public int insertSelective(MapSectionData record) {
        return mapSectionDataMapper.insertSelective(record);
    }

    @Override
    public int deleteBySectionId(String sectionId) {
        return mapSectionDataMapper.deleteByPrimaryKey(sectionId);
    }
}
