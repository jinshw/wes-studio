package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapService;

public interface MapServiceMapper {
    int deleteByPrimaryKey(Long msId);

    int insert(MapService record);

    int insertSelective(MapService record);

    MapService selectByPrimaryKey(Long msId);

    int updateByPrimaryKeySelective(MapService record);

    int updateByPrimaryKey(MapService record);
}