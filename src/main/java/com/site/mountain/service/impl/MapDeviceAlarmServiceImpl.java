package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.MapDeviceAlarmMapper;
import com.site.mountain.entity.MapDeviceAlarm;
import com.site.mountain.entity.PoiNode;
import com.site.mountain.entity.SysLog;
import com.site.mountain.service.MapDeviceAlarmService;
import com.site.mountain.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MapDeviceAlarmServiceImpl implements MapDeviceAlarmService {

    @Autowired
    private MapDeviceAlarmMapper mapDeviceAlarmMapper;

    public PageInfo<MapDeviceAlarm> selectListByPage(MapDeviceAlarm mapDeviceAlarm) {
        PageHelper.startPage(mapDeviceAlarm.getPageNum(), mapDeviceAlarm.getPageSize());
        List<MapDeviceAlarm> list = mapDeviceAlarmMapper.selectList(mapDeviceAlarm);
        PageInfo<MapDeviceAlarm> pageInfo = new PageInfo(list, mapDeviceAlarm.getPageSize());
        return pageInfo;
    }

    public ResponseEntity export(MapDeviceAlarm mapDeviceAlarm) {
        mapDeviceAlarm.setPageSize(100000000);
        mapDeviceAlarm.setPageNum(1);
        List<Map<String, Object>> list = mapDeviceAlarmMapper.selectListMap(mapDeviceAlarm);
        List<Map<String, Object>> rList = new ArrayList<>();
        Map<String, Object> map = null;
        for (int i = 0; i < list.size(); i++) {
            map = list.get(i);
            String deviceStatus = (String) map.get("device_status");
            if (deviceStatus.equals("1")) {
                map.put("device_status", "在线");
            } else if (deviceStatus.equals("2")) {
                map.put("device_status", "离线");
            } else if (deviceStatus.equals("3")) {
                map.put("device_status", "异常");
            }
            rList.add(map);
        }
        List<PoiNode> poiList = new ArrayList<>();
        poiList.add(new PoiNode("mda_id", "主键ID"));
        poiList.add(new PoiNode("device_code", "设备编码"));
        poiList.add(new PoiNode("device_type", "设备类型"));
        poiList.add(new PoiNode("device_status", "设备状态"));
        poiList.add(new PoiNode("alarm_content", "告警内容"));
        poiList.add(new PoiNode("create_time", "时间"));
        return PoiUtil.export2Excel("日志", poiList, rList);
    }


    @Override
    public int deleteByPrimaryKey(String mdaId) {
        return 0;
    }

    @Override
    public int insert(MapDeviceAlarm record) {
        return 0;
    }

    @Override
    public int insertSelective(MapDeviceAlarm record) {
        return 0;
    }

    @Override
    public MapDeviceAlarm selectByPrimaryKey(String mdaId) {
        return null;
    }

    @Override
    public List<MapDeviceAlarm> selectList(MapDeviceAlarm mapDeviceAlarm) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectListMap(MapDeviceAlarm mapDeviceAlarm) {
        return null;
    }
}
