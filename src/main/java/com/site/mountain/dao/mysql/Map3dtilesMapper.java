package com.site.mountain.dao.mysql;

import com.site.mountain.entity.Map3dtiles;

import java.util.List;

public interface Map3dtilesMapper {
    int deleteByPrimaryKey(Long tilesId);

    int insert(Map3dtiles record);

    int insertSelective(Map3dtiles record);

    Map3dtiles selectByPrimaryKey(Long tilesId);

    List<Map3dtiles> selectAll(Map3dtiles map3dtiles);

    int updateByPrimaryKeySelective(Map3dtiles record);

    int updateByPrimaryKey(Map3dtiles record);
}