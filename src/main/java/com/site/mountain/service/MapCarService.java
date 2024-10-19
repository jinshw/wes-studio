package com.site.mountain.service;

import com.site.mountain.entity.MapCar;
import com.site.mountain.entity.MapCarSpeed;
import com.site.mountain.entity.MapRsu;
import com.site.mountain.entity.MapShijian;

import java.util.List;

public interface MapCarService {

    List<MapCar> selectAll(MapCar mapCar);

    List<MapCar> findAllShuliang(MapCar mapCar);

    List<MapCar> findZaixianShuliang(MapCar mapCar);

    List<MapShijian> findMapShijianList(MapShijian mapShijian);

    List<MapRsu> findMapRsuList(MapRsu mapRsu);

    List<MapCarSpeed> findAllCarSpeed(MapCarSpeed mapCarSpeed);

    List<MapCarSpeed> findAllCarNow(MapCarSpeed mapCarSpeed);

}
