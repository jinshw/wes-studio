package com.site.mountain.service;

import com.site.mountain.entity.MapAccess;

public interface MapAccessService {
    int deleteByPrimaryKey(Long id);

    int insert(MapAccess record);

    int insertSelective(MapAccess record);

    MapAccess selectByPrimaryKey(Long id);

    MapAccess select(MapAccess mapAccess);

    int updateByPrimaryKeySelective(MapAccess record);

    int updateByPrimaryKey(MapAccess record);
}
