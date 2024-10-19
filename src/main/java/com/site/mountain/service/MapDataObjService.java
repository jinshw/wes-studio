package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDataObj;

public interface MapDataObjService {

    int insert(MapDataObj mapDataObj);

    PageInfo<MapDataObj> selectAll(MapDataObj mapDataObj);
}
