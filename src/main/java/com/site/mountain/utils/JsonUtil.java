package com.site.mountain.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

/**
 * 序列化工具类
 */
public class JsonUtil {
    private JsonUtil() {
    }


    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String mapToJson(Map<?, ?> map) {
        try {
            return MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> String objectToJson(T data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String json, TypeReference<T> reference) {
        try {
            return MAPPER.readValue(json, reference);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json数组转化为Double型
     * @param str
     * @return
     */
    public static Double[] getJsonToDoubleArray(String str) {
        JSONArray jsonArray = JSONArray.parseArray(str);
        Double[] arr=new Double[jsonArray.size()];
        for(int i=0;i<jsonArray.size();i++){
            arr[i]=jsonArray.getDouble(i);
        }
        return arr;
    }

    public static Double[] jsonArrayToDoubleArray(JSONArray jsonArray) {
        Double[] doubleArray = new Double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            doubleArray[i] = jsonArray.getDouble(i);
        }
        return doubleArray;
    }

    public static boolean isTwoDimensionalArray(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            Object element = jsonArray.get(i);
            if (element instanceof JSONArray) {
                // 递归检查内层JSONArray
                return isTwoDimensionalArray((JSONArray) element);
            } else if (element instanceof JSONObject) {
                // 如果出现JSONObject，则不是纯二维数组
                return false;
            }
        }
        // 所有元素都不是JSONArray，是一维数组
        return true;
    }

    public static Double[] jsonPointsToDoubleArray(String str) {
        Double[] points = null;
        JSONArray pointsArr = new JSONArray();
        if(str.contains("[[[")){
            pointsArr = JSONArray.parseArray(str).getJSONArray(0).getJSONArray(0);
            points = jsonArrayToDoubleArray(pointsArr);
        }else if(str.contains("[[")){
            pointsArr = JSONArray.parseArray(str).getJSONArray(0);
            points = jsonArrayToDoubleArray(pointsArr);
        }else{
            pointsArr = JSONArray.parseArray(str);
            points = jsonArrayToDoubleArray(pointsArr);
        }
        return points;
    }

    /**
     * double类型截取小数点后14位
     */
    public static double doubleCapture14(double value){
        BigDecimal bd = BigDecimal.valueOf(value).setScale(14, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        return roundedValue;
    }

    public static String doubleCapture14Str(double value){
        BigDecimal bd = BigDecimal.valueOf(value).setScale(14, RoundingMode.HALF_UP);
        double roundedValue = bd.doubleValue();
        return String.valueOf(roundedValue);
    }

    public static String pointsXYZCapture14Str(String pointsInfo){
        String result = "";
        JSONArray rePointsArr = new JSONArray();
        if(pointsInfo.contains("[[[")){
        }else if(pointsInfo.contains("[[")){
            JSONArray jsonArrayAll = JSONArray.parseArray(pointsInfo);
            for(int k=0;k<jsonArrayAll.size();k++){
                JSONArray jsonOne = jsonArrayAll.getJSONArray(k);
                double[] doubleArray = new double[jsonOne.size()];
                for (int i = 0; i < jsonOne.size(); i++) {
                    Double tempNew = 0.0;
                    if(jsonOne.getString(i).contains("E") || jsonOne.getString(i).contains("e")){
                        String temp = jsonOne.getString(i).replaceAll("E","").replaceAll("e","").replaceAll("\\+","");
                        tempNew =Double.parseDouble(temp)*100;
                    }else{
                        tempNew =Double.parseDouble(jsonOne.getString(i));
                    }
                    doubleArray[i] = doubleCapture14(tempNew);
                }
                rePointsArr.add(doubleArray);
            }
            result = rePointsArr.toJSONString();
        }else{
        }
        return result;
    }

    public static void main(String[] args) {
        String pointsInfo = "[[120.88316707550528222222,31.96902540133796111111,-0.004760489579262062],[120.883900182270618989898e9993,31.968211740620543,-0.004574839036778209],[120.88476305448044,31.96899289872452,-0.004352943302855929]]";
        String ss = pointsXYZCapture14Str(pointsInfo);
        System.out.println(ss);
    }
}
