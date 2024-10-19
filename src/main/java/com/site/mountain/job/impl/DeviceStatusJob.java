package com.site.mountain.job.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.job.BaseJob;
import com.site.mountain.service.MapDeviceService;
import com.site.mountain.service.MapVehicleService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DeviceStatusJob implements BaseJob {

    @Autowired
    private MapDeviceService mapDeviceService;

    @Autowired
    private MapVehicleService mapVehicleService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    private static Logger _log = LoggerFactory.getLogger(DeviceStatusJob.class);
    static int COUNT = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("DeviceStatusJob开始执行：" + new Date());
        List<JSONObject> devices = getMapDeviceList();
        List<JSONObject> vehicles = getMapVehicleList();
        COUNT = COUNT + 1;
        _log.info("第" + COUNT + "执行，获取设备数量：" + devices.size() + "  车辆数量：" + vehicles.size());
        kafkaTemplate.send("v2xserver-device-status", JSON.toJSONString(devices));
        kafkaTemplate.send("v2xserver-vehicle-status", JSON.toJSONString(vehicles));
    }

    private List<JSONObject> getMapDeviceList() {
        List<JSONObject> rList = new ArrayList<>();
        MapDevice mapDevice = new MapDevice();
        mapDevice.setPageSize(10000000);// 查询默认有分页，这里设置查询的最大数据量
        List<MapDevice> mapDevices = mapDeviceService.selectList(mapDevice);
        MapDevice tempDevice = null;
        JSONObject jsonObject = null;
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < mapDevices.size(); i++) {
            tempDevice = mapDevices.get(i);
            jsonObject = new JSONObject();
            jsonObject.put("id", tempDevice.getId());
            jsonObject.put("deviceCode", tempDevice.getDeviceCode());
            jsonObject.put("deviceType", tempDevice.getDeviceType());
            jsonObject.put("status", tempDevice.getStatus() == null ? "" : tempDevice.getStatus());
            jsonObject.put("updatetime", dff.format(tempDevice.getOptTime()));
            rList.add(jsonObject);
        }
        return rList;
    }

    private List<JSONObject> getMapVehicleList() {
        List<JSONObject> rList = new ArrayList<>();
        MapVehicle mapVehicle = new MapVehicle();
        mapVehicle.setPageSize(10000000);
        List<MapVehicle> list = mapVehicleService.selectList(mapVehicle);
        MapVehicle tempMapVehicle = null;
        JSONObject jsonObject = null;
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < list.size(); i++) {
            tempMapVehicle = list.get(i);
            jsonObject = new JSONObject();
            jsonObject.put("carId", tempMapVehicle.getVehicleId());
            jsonObject.put("carNumber", tempMapVehicle.getVehicleNumber());
            jsonObject.put("vin", tempMapVehicle.getVin());
            jsonObject.put("status", tempMapVehicle.getStatus());
            jsonObject.put("updatetime", dff.format(tempMapVehicle.getOptTime()));
            rList.add(jsonObject);
        }
        return rList;
    }

}
