package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.common.Method;
import com.site.mountain.entity.*;
import com.site.mountain.service.MapCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/mapCar")
public class MapCarController {

    @Autowired
    private MapCarService mapCarService;


    @RequestMapping(value = "findAllCar", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestBody MapCar mapCar) {
        List<MapCarRes> resList = new ArrayList<>();
        if (mapCar == null) {
            mapCar = new MapCar();
        }
        String bearing = mapCar.getBearing();
        if (bearing == null || bearing == "") {
            mapCar.setBearing("在线");
        }
        JSONObject jsonObject = new JSONObject();
        List<MapCar> list = mapCarService.selectAll(mapCar);
        for (MapCar MapCar : list) {
            MapCarRes car = new MapCarRes();
            BeanUtils.copyProperties(MapCar, car);
            Coordinate c = new Coordinate();
            c.setX(MapCar.getLon());
            c.setY(MapCar.getLat());
            car.setCoordinate(c);
            resList.add(car);
        }
        jsonObject.put("success", true);
        jsonObject.put("data", resList);
        return jsonObject;
    }


    @RequestMapping(value = "findCarByFenlei", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findCarByFenlei(@RequestBody MapCar mapCarParam) {
        if (mapCarParam == null) {
            mapCarParam = new MapCar();
        }

        List<MapCar> mapCarList = mapCarService.findAllShuliang(new MapCar());
        List<MapCar> mapCarZaixianList = mapCarService.findZaixianShuliang(new MapCar());
        JSONArray jsonCar = new JSONArray();

        JSONObject jsonZaixian0 = new JSONObject();
        jsonZaixian0.put("brand", "一汽");
        jsonZaixian0.put("zaixian", "1/1");
        jsonCar.add(jsonZaixian0);


        for (MapCar mapCar : mapCarList) {
            String brand = mapCar.getBrand();
            int numMapCar = mapCar.getId();
            if (brand.equals("一汽")) {
                continue;
            }
            JSONObject jsonZaixian = new JSONObject();
            jsonZaixian.put("brand", brand);
//			jsonZaixian.put("allNum",mapCar[1]);
            for (MapCar mapCarZaixian : mapCarZaixianList) {
                String brandZaixian = mapCarZaixian.getBrand();
                int numZaixian = mapCarZaixian.getId();
                if (brandZaixian.equals(brand)) {
                    jsonZaixian.put("zaixian", numZaixian + "/" + numMapCar);
                }
            }
            if (!jsonZaixian.containsKey("zaixian")) {
                jsonZaixian.put("zaixian", 0 + "/" + numMapCar);
            }
            jsonCar.add(jsonZaixian);
        }

        JSONObject jsonkey = new JSONObject();
        jsonkey.put("bearing", "在线");

        MapCar tempMapCar = new MapCar();
        tempMapCar.setBearing("在线");

        List<MapCar> mapCarAllList = mapCarService.selectAll(tempMapCar);
        List<double[]> zuobiaoList = new ArrayList<>();
        for (MapCar MapCar : mapCarAllList) {
            double[] zuobiao = {MapCar.getLon(), MapCar.getLat()};
            zuobiaoList.add(zuobiao);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("zuobiao", zuobiaoList);
        jsonObject.put("data", jsonCar);
        jsonObject.put("success", true);
        return jsonObject;
    }


    @RequestMapping(value = "findShijian", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findShijian(@RequestBody MapShijian mapShijian) {
        List<MapShijianRes> zuobiaoList = new ArrayList<>();
        if (mapShijian == null) {
            mapShijian = new MapShijian();
        }
        JSONObject jsonObject = new JSONObject();
        List<MapShijian> list = mapCarService.findMapShijianList(mapShijian);
        for (MapShijian mapShijianTemp : list) {
            MapShijianRes mapShijianRes = new MapShijianRes();
            BeanUtils.copyProperties(mapShijianTemp, mapShijianRes);
            Coordinate c = new Coordinate();
            c.setX(mapShijianTemp.getLon());
            c.setY(mapShijianTemp.getLat());
            mapShijianRes.setCoordinate(c);
            zuobiaoList.add(mapShijianRes);
        }
        jsonObject.put("success", true);
        jsonObject.put("data", zuobiaoList);
        return jsonObject;
    }

    @RequestMapping(value = "findMapRsu", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findMapRsu(@RequestBody MapRsu mapRsu) {
        List<MapRsuRes> zuobiaoList = new ArrayList<>();
        if (mapRsu == null) {
            mapRsu = new MapRsu();
        }
        JSONObject jsonObject = new JSONObject();
        List<MapRsu> list = mapCarService.findMapRsuList(mapRsu);
        for (MapRsu mapRsuTemp : list) {
            MapRsuRes mapRsuRes = new MapRsuRes();
            BeanUtils.copyProperties(mapRsuTemp, mapRsuRes);
            Coordinate c = new Coordinate();
            c.setX(mapRsuTemp.getLon());
            c.setY(mapRsuTemp.getLat());
            mapRsuRes.setCoordinate(c);
            mapRsuRes.setFangwei(Method.mathsuijiDouble(0, 180));
            zuobiaoList.add(mapRsuRes);
        }
        jsonObject.put("success", true);
        jsonObject.put("data", zuobiaoList);
        return jsonObject;
    }

    @RequestMapping(value = "findAllCarSpeed", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAllCarSpeed(@RequestBody MapCarSpeed mapCarSpeed) {
        if (mapCarSpeed == null) {
            mapCarSpeed = new MapCarSpeed();
        }
        JSONObject jsonObject = new JSONObject();
        List<MapCarSpeed> list = mapCarService.findAllCarSpeed(mapCarSpeed);

        JSONArray jsonres = new JSONArray();
        JSONObject jsonspeedVid = new JSONObject();
        int a = 0;
        for (MapCarSpeed mapCarSpeedTemp : list) {
            if (jsonspeedVid.containsKey(mapCarSpeedTemp.getVid())) {
                JSONObject jsonObjectTemp = jsonspeedVid.getJSONObject(mapCarSpeedTemp.getVid());
                JSONArray weizhiList = jsonObjectTemp.getJSONArray("weizhi");
                JSONArray weizhi = new JSONArray();
                weizhi.add(mapCarSpeedTemp.getLon());
                weizhi.add(mapCarSpeedTemp.getLat());
                weizhiList.add(weizhi);
                jsonObjectTemp.put("weizhi", weizhiList);
                jsonspeedVid.put(mapCarSpeedTemp.getVid(), jsonObjectTemp);
            } else {
                JSONArray weizhiList = new JSONArray();
                JSONArray weizhi = new JSONArray();
                weizhi.add(mapCarSpeedTemp.getLon());
                weizhi.add(mapCarSpeedTemp.getLat());
                weizhiList.add(weizhi);
                JSONObject jsonObjectTemp = new JSONObject();
                jsonObjectTemp.put("speed", mapCarSpeedTemp.getSpeed());
                jsonObjectTemp.put("weizhi", weizhiList);
                jsonspeedVid.put(mapCarSpeedTemp.getVid(), jsonObjectTemp);
            }
            a++;
            if (a > 4000) {
                break;
            }
        }

        Iterator iterator = jsonspeedVid.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();

            JSONObject listweizhi = jsonspeedVid.getJSONObject(key);
            jsonres.add(listweizhi);
        }

        jsonObject.put("success", true);
        jsonObject.put("data", jsonres);
        return jsonObject;
    }

    @RequestMapping(value = "findAllCarNow", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAllCarNow(@RequestBody MapCarSpeed mapCarSpeed) {
        if (mapCarSpeed == null) {
            mapCarSpeed = new MapCarSpeed();
        }
        JSONObject jsonObject = new JSONObject();
        List<MapCarSpeed> list = mapCarService.findAllCarNow(mapCarSpeed);


        JSONArray jsonres = new JSONArray();
        JSONObject jsonspeedVid = new JSONObject();
        String vid = "";
        String timestamp1 = "";
        int a = 0;
        for (MapCarSpeed mapCarSpeedTemp : list) {
            if (vid.equals(mapCarSpeedTemp.getVid()) && timestamp1.equals(mapCarSpeedTemp.getTimestamp1())) {
                continue;
            }
            vid = mapCarSpeedTemp.getVid();
            timestamp1 = mapCarSpeedTemp.getTimestamp1();
            if (jsonspeedVid.containsKey(mapCarSpeedTemp.getTimestamp1())) {
                JSONArray jsonObjectTemp = jsonspeedVid.getJSONArray(mapCarSpeedTemp.getTimestamp1());
                JSONArray weizhi = new JSONArray();
                weizhi.add(mapCarSpeedTemp.getLon());
                weizhi.add(mapCarSpeedTemp.getLat());
                if (mapCarSpeedTemp.getBearing() != null) {
                    weizhi.add(mapCarSpeedTemp.getBearing());
                } else {
                    weizhi.add(90);
                }

                jsonObjectTemp.add(weizhi);
                jsonspeedVid.put(mapCarSpeedTemp.getTimestamp1(), jsonObjectTemp);
            } else {
                JSONArray weizhiList = new JSONArray();
                JSONArray weizhi = new JSONArray();
                weizhi.add(mapCarSpeedTemp.getLon());
                weizhi.add(mapCarSpeedTemp.getLat());
                if (mapCarSpeedTemp.getBearing() != null) {
                    weizhi.add(mapCarSpeedTemp.getBearing());
                } else {
                    weizhi.add(90);
                }
                weizhiList.add(weizhi);
                jsonspeedVid.put(mapCarSpeedTemp.getTimestamp1(), weizhiList);
            }
            a++;
            if (a > 4000) {
                break;
            }
        }

        Iterator iterator = jsonspeedVid.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            jsonres.add(jsonspeedVid.getJSONArray(key));
        }

        jsonObject.put("success", true);
        jsonObject.put("data", jsonres);
        return jsonObject;
    }

}
