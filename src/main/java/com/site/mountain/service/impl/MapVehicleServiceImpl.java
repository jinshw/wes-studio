package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapVehicleMapper;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.PoiNode;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapVehicleService;
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
public class MapVehicleServiceImpl implements MapVehicleService {

    @Autowired
    private MapVehicleMapper mapVehicleMapper;

    public PageInfo<MapVehicle> selectListByPage(MapVehicle mapVehicle) {
        PageHelper.startPage(mapVehicle.getPageNum(), mapVehicle.getPageSize());
        List<MapVehicle> list = mapVehicleMapper.selectList(mapVehicle);
        PageInfo<MapVehicle> pageInfo = new PageInfo(list, mapVehicle.getPageSize());
        return pageInfo;
    }

    public ResponseEntity export(MapVehicle mapVehicle) {
        mapVehicle.setPageSize(1000000);
        List<Map<String, Object>> list = mapVehicleMapper.selectListMap(mapVehicle);
        list = optionsToName(list);

        List<PoiNode> poiList = new ArrayList<>();
        poiList.add(new PoiNode("vehicle_number", "车牌号"));
        poiList.add(new PoiNode("vin", "车架VIN号"));
        poiList.add(new PoiNode("brand", "品牌"));
        poiList.add(new PoiNode("model", "型号"));
        poiList.add(new PoiNode("vehicle_type", "车辆类型"));
        poiList.add(new PoiNode("enterprises", "所属企业"));
        poiList.add(new PoiNode("driving_level", "自动驾驶等级"));
        poiList.add(new PoiNode("has_obu", "是否有OBU:1有，2没有"));
        poiList.add(new PoiNode("driver_name", "驾驶人姓名"));
        poiList.add(new PoiNode("phone", "联系电话"));
        poiList.add(new PoiNode("status", "车辆状态"));
        return PoiUtil.export2Excel("vehicle", poiList, list);
    }

    @Override
    public int deleteByPrimaryKey(Long vehicleId) {
        return mapVehicleMapper.deleteByPrimaryKey(vehicleId);
    }

    @Override
    public int insert(MapVehicle record) {
        return mapVehicleMapper.insert(record);
    }

    @Override
    public int insertSelective(MapVehicle record) {
        return mapVehicleMapper.insertSelective(record);
    }

    @Override
    public MapVehicle selectByPrimaryKey(Long vehicleId) {
        return mapVehicleMapper.selectByPrimaryKey(vehicleId);
    }

    @Override
    public List<MapVehicle> selectList(MapVehicle mapVehicle) {
        return mapVehicleMapper.selectList(mapVehicle);
    }

    @Override
    public int updateByPrimaryKeySelective(MapVehicle record) {
        return mapVehicleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(MapVehicle record) {
        return mapVehicleMapper.updateByPrimaryKey(record);
    }

    public int deleteList(List<MapVehicle> list) {
        MapVehicle mapVehicle = null;
        MapVehicle temp = new MapVehicle();
        temp.setIsDel("2");// 是否删除：1正常，2删除

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            temp.setOptPerson(sysUser.getUserId().longValue());
            temp.setOptTime(timestamp);
        }

        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            mapVehicle = list.get(i);
            temp.setVehicleId(mapVehicle.getVehicleId());
            mapVehicleMapper.updateByPrimaryKeySelective(temp);
            result = result + 1;
        }
        return result;
    }

    public List<Map<String, Object>> optionsToName(List<Map<String, Object>> list) {
        List<Map<String, Object>> rList = new ArrayList<>();
        Map<String, Object> stringObjectMap = null;
        for (int i = 0; i < list.size(); i++) {
            stringObjectMap = list.get(i);
            String vehicleType = (String) stringObjectMap.get("vehicle_type");
            switch (vehicleType){
                case "0":
                    vehicleType = "未知车辆";
                    break;
                case "10":
                    vehicleType = "乘用车";
                    break;
                case "25":
                    vehicleType = "卡车";
                    break;
                case "40":
                    vehicleType = "摩托车";
                    break;
                case "50":
                    vehicleType = "运输车";
                    break;
                case "60":
                    vehicleType = "应急车";
                    break;
                case "99":
                    vehicleType = "其他类型";
                    break;
                default:
                    vehicleType = "";

            }
            stringObjectMap.put("vehicle_type",vehicleType);
            rList.add(stringObjectMap);
        }
        return rList;
    }


}


