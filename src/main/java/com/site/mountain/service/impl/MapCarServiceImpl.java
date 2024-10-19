package com.site.mountain.service.impl;

import com.site.mountain.dao.test1.MapCarMapper;
import com.site.mountain.dao.test1.MapCarSpeedMapper;
import com.site.mountain.dao.test1.MapRsuMapper;
import com.site.mountain.dao.test1.MapShijianMapper;
import com.site.mountain.entity.MapCar;
import com.site.mountain.entity.MapCarSpeed;
import com.site.mountain.entity.MapRsu;
import com.site.mountain.entity.MapShijian;
import com.site.mountain.service.MapCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapCarServiceImpl implements MapCarService {

    @Autowired
    private MapCarMapper mapCarMapper;

    @Autowired
    private MapShijianMapper mapShijianMapper;

    @Autowired
    private MapRsuMapper mapRsuMapper;

    @Autowired
    private MapCarSpeedMapper mapCarSpeedMapper;

    public List<MapCar> selectAll(MapCar mapCar) {
        return mapCarMapper.selectAll(mapCar);
    }

    public List<MapCar> findAllShuliang(MapCar mapCar) {
        return mapCarMapper.findAllShuliang(mapCar);
    }

    public List<MapCar> findZaixianShuliang(MapCar mapCar) {
        return mapCarMapper.findZaixianShuliang(mapCar);
    }


    public List<MapShijian> findMapShijianList(MapShijian mapShijian) {
        return mapShijianMapper.selectAll(mapShijian);
    }

    public List<MapRsu> findMapRsuList(MapRsu mapRsu) {
        return mapRsuMapper.selectAll(mapRsu);
    }

    public List<MapCarSpeed> findAllCarSpeed(MapCarSpeed mapCarSpeed) {
        return mapCarSpeedMapper.findAllCarSpeed(mapCarSpeed);
    }

    public List<MapCarSpeed> findAllCarNow(MapCarSpeed mapCarSpeed) {
        return mapCarSpeedMapper.findAllCarNow(mapCarSpeed);
    }

}
