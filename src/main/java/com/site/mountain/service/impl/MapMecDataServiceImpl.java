package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapMecDataMapper;
import com.site.mountain.entity.MapMec;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.service.MapMecDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapMecDataServiceImpl implements MapMecDataService {

    @Autowired
    private MapMecDataMapper mapMecDataMapper;

    @Override
    public int insertSelective(MapMecData record) {
        return mapMecDataMapper.insertSelective(record);
    }

    public PageInfo<MapMecData> selectList(MapMecData mapMecData) {
        PageHelper.startPage(mapMecData.getPageNum(), mapMecData.getPageSize());
        List<MapMecData> list = mapMecDataMapper.selectList(mapMecData);
        PageInfo<MapMecData> pageInfo = new PageInfo(list, mapMecData.getPageSize());
        return pageInfo;
    }


    public int delete(MapMecData mapMecData){
        return mapMecDataMapper.delete(mapMecData);
    }

    @Override
    public int deleteBySectionId(String sectionId){
        return mapMecDataMapper.deleteBySectionId(sectionId);
    }
}
