package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.Map3dtilesMapper;
import com.site.mountain.entity.Map3dtiles;
import com.site.mountain.service.Map3dtilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Map3dtilesServiceImpl implements Map3dtilesService {

    @Autowired
    Map3dtilesMapper map3dtilesMapper;

    @Override
    public PageInfo<Map3dtiles> selectAll(Map3dtiles map3dtiles) {
        PageHelper.startPage(map3dtiles.getPageNum(), map3dtiles.getPageSize());
        List<Map3dtiles> list = map3dtilesMapper.selectAll(map3dtiles);
        PageInfo<Map3dtiles> pageInfo = new PageInfo<>(list, map3dtiles.getPageSize());
        return pageInfo;
    }

    public int add(Map3dtiles map3dtiles) {
        return map3dtilesMapper.insertSelective(map3dtiles);
    }

    public int delete(Map3dtiles map3dtiles) {
        return map3dtilesMapper.deleteByPrimaryKey(map3dtiles.getTilesId());
    }

    public int update(Map3dtiles map3dtiles) {
        return map3dtilesMapper.updateByPrimaryKeySelective(map3dtiles);
    }


}
