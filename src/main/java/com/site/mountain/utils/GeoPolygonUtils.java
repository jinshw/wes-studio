package com.site.mountain.utils;

import com.alibaba.fastjson.JSONArray;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoPolygonUtils {
    public static void main(String[] args) {
        /*String lng_lat = "118.123214,33.363985;118.130634,33.364412;118.130203,33.360401;118.127867,33.360733;118.127903,33.364683;";
        Map<String,String> map = getCenterPoint(lng_lat);
        System.out.println("中心点"+map.get("lng") +","+ map.get("lat"));*/

        String polygon = "[[[120.88397842467317,31.982271825323195,0],[120.88380756569939,31.98089100912236,0],[120.88296679194023,31.980768644802016,0],[120.88311719286752,31.980028719345846,0],[120.88468253639874,31.978741150445533,0],[120.88574202272144,31.979009160464855,0],[120.88632304415607,31.980652145634497,0],[120.88616583010557,31.981217285896374,0],[120.88473034874679,31.98238252812371,0],[120.88397842467317,31.982271825323195,0]]]";
        if(polygon.contains("[[[")){
            JSONArray pointsArr = JSONArray.parseArray(polygon).getJSONArray(0);
            String point = getCenterPoint(pointsArr);
        }
    }

    /**
     * 获取多边形中心点
     */
    public static String getCenterPoint(JSONArray pointsArr) {
        int total = pointsArr.size();
        double X = 0, Y = 0, Z = 0;
        for (int i = 0; i < pointsArr.size(); i++) {
            JSONArray pointOne = pointsArr.getJSONArray(i);
            double lat, lon, x, y, z;
            lon = pointOne.getDouble(0) * Math.PI / 180;
            lat = pointOne.getDouble(1) * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        DecimalFormat df = new DecimalFormat("#0.00000000000000");
        JSONArray jsonArray = new JSONArray();
        String lonS = df.format(Lon * 180 / Math.PI);
        String latS = df.format(Lat * 180 / Math.PI);
        jsonArray.add(Double.parseDouble(lonS));
        jsonArray.add(Double.parseDouble(latS));
        return jsonArray.toJSONString();
    }

    /**
     * 获取多边形中心点
     */
    public static Map<String, String> getCenterPoint(String str) {
        String[] arr = str.split(";");
        int total = arr.length;
        double X = 0, Y = 0, Z = 0;
        for (int i = 0; i < arr.length; i++) {
            double lat, lon, x, y, z;
            lon = Double.parseDouble(arr[i].split(",")[0]) * Math.PI / 180;
            lat = Double.parseDouble(arr[i].split(",")[1]) * Math.PI / 180;
            x = Math.cos(lat) * Math.cos(lon);
            y = Math.cos(lat) * Math.sin(lon);
            z = Math.sin(lat);
            X += x;
            Y += y;
            Z += z;
        }

        X = X / total;
        Y = Y / total;
        Z = Z / total;
        double Lon = Math.atan2(Y, X);
        double Hyp = Math.sqrt(X * X + Y * Y);
        double Lat = Math.atan2(Z, Hyp);
        DecimalFormat df = new DecimalFormat("#0.00000000000000");
        Map<String, String> map = new HashMap<>();
        map.put("lng", df.format(Lon * 180 / Math.PI));
        map.put("lat", df.format(Lat * 180 / Math.PI));
        return map;
    }

}
