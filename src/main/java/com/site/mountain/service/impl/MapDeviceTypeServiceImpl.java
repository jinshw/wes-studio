package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapDeviceTypeMapper;
import com.site.mountain.entity.MapDeviceType;
import com.site.mountain.service.MapDeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MapDeviceTypeServiceImpl implements MapDeviceTypeService {

    @Autowired
    private MapDeviceTypeMapper mapDeviceTypeMapper;

    @Override
    public int insert(MapDeviceType record) {
        return mapDeviceTypeMapper.insert(record);
    }

    @Override
    public MapDeviceType selectByPrimaryKey(Long dtId) {
        return mapDeviceTypeMapper.selectByPrimaryKey(dtId);
    }

    @Override
    public List<MapDeviceType> selectList(MapDeviceType deviceType) {
        return mapDeviceTypeMapper.selectList(deviceType);
    }
    @Override
    public List<MapDeviceType> selectRelation(MapDeviceType deviceType) {
        return mapDeviceTypeMapper.selectRelation(deviceType);
    }

    public PageInfo<MapDeviceType> selectListByPage(MapDeviceType mapDeviceType) {
        PageHelper.startPage(mapDeviceType.getPageNum(), mapDeviceType.getPageSize());
        List<MapDeviceType> list = mapDeviceTypeMapper.selectList(mapDeviceType);
        PageInfo<MapDeviceType> pageInfo = new PageInfo(list, mapDeviceType.getPageSize());
        return pageInfo;
    }

    public int updateByPrimaryKeySelective(MapDeviceType record){
        return mapDeviceTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByPrimaryKey(Long dtId){
        return mapDeviceTypeMapper.deleteByPrimaryKey(dtId);
    }
}
