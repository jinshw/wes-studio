package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.MapAccessMapper;
import com.site.mountain.entity.MapAccess;
import com.site.mountain.service.MapAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MapAccessServiceImpl implements MapAccessService {

    @Autowired
    private MapAccessMapper mapAccessMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(MapAccess record) {
        return mapAccessMapper.insert(record);
    }

    @Override
    public int insertSelective(MapAccess record) {
        return mapAccessMapper.insertSelective(record);
    }

    @Override
    public MapAccess selectByPrimaryKey(Long id) {
        return mapAccessMapper.selectByPrimaryKey(id);
    }

    @Override
    public MapAccess select(MapAccess mapAccess) {
        return mapAccessMapper.select(mapAccess);
    }

    @Override
    public int updateByPrimaryKeySelective(MapAccess record) {
        return mapAccessMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MapAccess record) {
        return mapAccessMapper.updateByPrimaryKey(record);
    }
}
