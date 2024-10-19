package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapMecDataMapper;
import com.site.mountain.dao.mysql.MapMecMapper;
import com.site.mountain.entity.MapMec;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.service.MapMecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapMecServiceImpl implements MapMecService {

    @Autowired
    private MapMecMapper mapMecMapper;
    @Autowired
    private MapMecDataMapper mapMecDataMapper;

    public int insert(MapMec record) {
        return mapMecMapper.insert(record);
    }

    public PageInfo<MapMec> selectList(MapMec mapMec) {
        PageHelper.startPage(mapMec.getPageNum(), mapMec.getPageSize());
        List<MapMec> list = mapMecMapper.selectList(mapMec);
        PageInfo<MapMec> pageInfo = new PageInfo(list, mapMec.getPageSize());
        return pageInfo;
    }

    public MapMec selectByPrimaryKey(MapMec mapMec) {
        return mapMecMapper.selectByPrimaryKey(mapMec);
    }

    public PageInfo<MapMecData> mapdatalist(MapMec mapMec) {
        MapMecData mapMecData = new MapMecData();
        mapMecData.setMecId(mapMec.getMecId());
        PageHelper.startPage(mapMec.getPageNum(), mapMec.getPageSize());
        List<MapMecData> list = mapMecDataMapper.selectList(mapMecData);
        PageInfo<MapMecData> pageInfo = new PageInfo(list, mapMec.getPageSize());
        return pageInfo;
    }


    public int add(MapMec mapMec) {
        return mapMecMapper.insert(mapMec);
    }

    public int delete(Long dataId) {
        return mapMecMapper.deleteByPrimaryKey(dataId);
    }

    public int updateByPrimaryKeySelective(MapMec mapMec) {
        return mapMecMapper.updateByPrimaryKeySelective(mapMec);
    }


}
