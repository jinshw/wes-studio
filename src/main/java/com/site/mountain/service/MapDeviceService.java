package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.MapDeviceType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MapDeviceService {

    List<MapDevice> selectList(MapDevice mapDevice);

    PageInfo<MapDevice> selectListByPage(MapDevice mapDevice);

    List<MapDevice> selectGltfList(MapDevice mapDevice);


    int updateByPrimaryKeySelective(MapDevice record);

    int deleteList(List<MapDevice> list);

    MapDevice selectByPrimaryKey(Long id);

    int insertSelective(MapDevice record);

    int deleteByPrimaryKey(Long id);

    ResponseEntity export(MapDevice mapDevice);

    List<MapDevice> selectListByDeviceType(MapDevice mapDevice);

    List<MapDevice> selectListByDeviceTypes(MapDevice mapDevice);
}
