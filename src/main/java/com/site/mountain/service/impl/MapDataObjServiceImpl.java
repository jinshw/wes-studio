package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapDataObjMapper;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapDataObj;
import com.site.mountain.service.MapDataObjService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapDataObjServiceImpl implements MapDataObjService {

    @Autowired
    private MapDataObjMapper mapDataObjMapper;

    public int insert(MapDataObj mapDataObj) {
        return mapDataObjMapper.insert(mapDataObj);
    }

    @Override
    public PageInfo<MapDataObj> selectAll(MapDataObj mapDataObj) {
        PageHelper.startPage(mapDataObj.getPageNum(), mapDataObj.getPageSize());
        List<MapDataObj> list = mapDataObjMapper.selectAll(mapDataObj);
        PageInfo<MapDataObj> pageInfo = new PageInfo(list, mapDataObj.getPageSize());
        return pageInfo;
    }

}
