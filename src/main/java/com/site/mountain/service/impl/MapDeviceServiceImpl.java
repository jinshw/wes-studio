package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapDeviceMapper;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.PoiNode;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapDeviceService;
import com.site.mountain.utils.PoiUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MapDeviceServiceImpl implements MapDeviceService {

    @Autowired
    private MapDeviceMapper mapDeviceMapper;

    @Override
    public List<MapDevice> selectList(MapDevice mapDevice) {
        return mapDeviceMapper.selectList(mapDevice);
    }

    @Override
    public PageInfo<MapDevice> selectListByPage(MapDevice mapDevice) {
        PageHelper.startPage(mapDevice.getPageNum(), mapDevice.getPageSize());
        List<MapDevice> list = mapDeviceMapper.selectList(mapDevice);
        PageInfo<MapDevice> pageInfo = new PageInfo(list, mapDevice.getPageSize());
        return pageInfo;
    }
    @Override
    public List<MapDevice> selectGltfList(MapDevice mapDevice) {
        PageHelper.startPage(mapDevice.getPageNum(), mapDevice.getPageSize());
        List<MapDevice> list = mapDeviceMapper.selectGLTF(mapDevice);
        return list;
    }

    @Override
    public int updateByPrimaryKeySelective(MapDevice record) {
        return mapDeviceMapper.updateByPrimaryKeySelective(record);
    }

    public int deleteList(List<MapDevice> list) {
        MapDevice mapDevice = null;
        MapDevice temp = new MapDevice();
        temp.setIsDel("1");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            temp.setOptPerson(sysUser.getUserId().longValue());
            temp.setOptTime(timestamp);
        }

        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            mapDevice = list.get(i);
            temp.setId(mapDevice.getId());
            mapDeviceMapper.updateByPrimaryKeySelective(temp);
            result = result + 1;
        }
        return result;
    }

    @Override
    public MapDevice selectByPrimaryKey(Long id) {
        return mapDeviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertSelective(MapDevice record) {
        return mapDeviceMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapDeviceMapper.deleteByPrimaryKey(id);
    }

    public ResponseEntity export(MapDevice mapDevice){
        List<Map<String, Object>> list = mapDeviceMapper.selectListMap(mapDevice);
        List<PoiNode> poiList = new ArrayList<>();
        poiList.add(new PoiNode("deviceCode","设备编号"));
        poiList.add(new PoiNode("deviceType","设备类别"));
        poiList.add(new PoiNode("brand","品牌"));
        poiList.add(new PoiNode("deviceModel","型号"));
        poiList.add(new PoiNode("serialNumber","序列号"));
        poiList.add(new PoiNode("poleCode","杆柱编号"));
        poiList.add(new PoiNode("orientation","朝向"));
        poiList.add(new PoiNode("connectMec","所属MEC"));
        poiList.add(new PoiNode("roadLoction","道路上的位置"));
        poiList.add(new PoiNode("lon","经度"));
        poiList.add(new PoiNode("lat","纬度"));
        poiList.add(new PoiNode("alt","高程"));
        poiList.add(new PoiNode("ip","设备IP"));
        poiList.add(new PoiNode("mesh","图幅号"));
        poiList.add(new PoiNode("mask","掩码"));
        poiList.add(new PoiNode("gateway","网关"));
        poiList.add(new PoiNode("serverConnect","服务连接地址"));
        poiList.add(new PoiNode("status","设备状态"));
        poiList.add(new PoiNode("effectiveRadius","有效半径(米)"));
        return PoiUtil.export2Excel("vehicle",poiList,list);
    }

    @Override
    public List<MapDevice> selectListByDeviceType(MapDevice mapDevice) {
        return mapDeviceMapper.selectListByDeviceType(mapDevice);
    }

    public List<MapDevice> selectListByDeviceTypes(MapDevice mapDevice) {
        return mapDeviceMapper.selectListByDeviceTypes(mapDevice);
    }
}
