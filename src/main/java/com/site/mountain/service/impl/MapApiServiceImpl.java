package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.MapDataMapper;
import com.site.mountain.dao.mysql.MapDataObjMapper;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapDataObj;
import com.site.mountain.service.MapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapApiServiceImpl implements MapApiService {

    @Autowired
    private MapDataMapper mapDataMapper;

    @Autowired
    private MapDataObjMapper mapDataObjMapper;

    /**
     * 获取Obj模型数据
     *
     * @param mapDataObj 参数：x,y,code,distance
     * @return
     */
    public List<MapDataObj> getObjByXYDistance(MapData mapDataMain, MapDataObj mapDataObj) {
        List<MapDataObj> resultList = new ArrayList();
        MapDataObj mapDataObjParam = new MapDataObj();
        mapDataObjParam.setDataId(mapDataMain.getDataId());
        List<MapDataObj> mapDataObjs = mapDataObjMapper.selectAll(mapDataObjParam);
        MapDataObj item = null;
        double xSquare = 0.0;
        double ySquare = 0.0;
        Double xDouble = 0.0;
        Double yDouble = 0.0;
        Double xDoubleParam = Double.parseDouble(mapDataObj.getX());
        Double yDoubleParam = Double.parseDouble(mapDataObj.getY());
        Double distanceParam = mapDataObj.getDistance();
        for (int i = 0; i < mapDataObjs.size(); i++) {
            item = mapDataObjs.get(i);
            xDouble = Double.parseDouble(item.getX());
            yDouble = Double.parseDouble(item.getY());
            xSquare = (xDouble - xDoubleParam) * (xDouble - xDoubleParam);
            ySquare = (yDouble - yDoubleParam) * (yDouble - yDoubleParam);
            // 两个点之间距离
            double distanceResult = Math.sqrt(xSquare + ySquare);
            // 点符合距离存储在结果list中
            if (distanceParam.doubleValue() >= distanceResult) {
                resultList.add(item);
            }
        }
        return resultList;
    }
}
