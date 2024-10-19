package com.site.mountain.dao.mysql;

import com.site.mountain.entity.MapDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Mapper
public interface MapDeviceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MapDevice record);

    int insertSelective(MapDevice record);

    MapDevice selectByPrimaryKey(Long id);

    List<MapDevice> selectList(MapDevice mapDevice);
    List<MapDevice> selectGLTF(MapDevice mapDevice);

    List<Map<String,Object>> selectListMap(MapDevice mapDevice);

    int updateByPrimaryKeySelective(MapDevice record);

    int updateByPrimaryKey(MapDevice record);

    List<MapDevice> selectListByDeviceType(MapDevice mapDevice);

    List<MapDevice> selectListByDeviceId(Long deviceId);

    List<MapDevice> selectListByDeviceTypes(MapDevice mapDevice);
}