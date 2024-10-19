package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapMec;
import com.site.mountain.entity.MapMecData;

public interface MapMecService {

    int insert(MapMec record);

    PageInfo<MapMec> selectList(MapMec mapMec);

    MapMec selectByPrimaryKey(MapMec mapMec);

    PageInfo<MapMecData> mapdatalist(MapMec mapMec) ;

    int add(MapMec mapMec);

    int delete(Long dataId);

    int updateByPrimaryKeySelective(MapMec mapMec);


}
