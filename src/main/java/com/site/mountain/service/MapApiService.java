package com.site.mountain.service;

import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapDataObj;

import java.util.List;

public interface MapApiService {

    List<MapDataObj> getObjByXYDistance(MapData mapDataMain, MapDataObj mapDataObj);
}
