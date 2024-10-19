package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapVehicleAlarmMapper;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.MapVehicleAlarm;
import com.site.mountain.entity.PoiNode;
import com.site.mountain.service.MapVehicleAlarmService;
import com.site.mountain.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MapVehicleAlarmServiceImpl implements MapVehicleAlarmService {

    @Autowired
    private MapVehicleAlarmMapper mapVehicleAlarmMapper;

    @Override
    public List<MapVehicleAlarm> selectList(MapVehicleAlarm mapVehicleAlarm) {
        return mapVehicleAlarmMapper.selectList(mapVehicleAlarm);
    }

    @Override
    public PageInfo<MapVehicleAlarm> selectListByPage(MapVehicleAlarm mapVehicleAlarm) {
        PageHelper.startPage(mapVehicleAlarm.getPageNum(), mapVehicleAlarm.getPageSize());
        List<MapVehicleAlarm> list = mapVehicleAlarmMapper.selectList(mapVehicleAlarm);
        PageInfo<MapVehicleAlarm> pageInfo = new PageInfo(list, mapVehicleAlarm.getPageSize());
        return pageInfo;
    }

    public ResponseEntity export(MapVehicleAlarm mapVehicleAlarm){
        mapVehicleAlarm.setPageSize(1000000);
        List<Map<String, Object>> list = mapVehicleAlarmMapper.selectListMap(mapVehicleAlarm);
        List<PoiNode> poiList = new ArrayList<>();
        poiList.add(new PoiNode("va_id","ID"));
        poiList.add(new PoiNode("alarm_name","告警名称"));
        poiList.add(new PoiNode("alarm_type","告警类型"));
        poiList.add(new PoiNode("plate_no","车牌号"));
        poiList.add(new PoiNode("enterprise","所属企业"));
        poiList.add(new PoiNode("location","告警位置"));
        poiList.add(new PoiNode("lon","经度"));
        poiList.add(new PoiNode("lat","纬度"));
        poiList.add(new PoiNode("ele","高程"));
        poiList.add(new PoiNode("alarm_time","告警时间"));
        return PoiUtil.export2Excel("车辆告警",poiList,list);
    }
}
