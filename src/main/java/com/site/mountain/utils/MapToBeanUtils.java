package com.site.mountain.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MapToBeanUtils {

    public static void mapToBean(Map<String, Object> map, Object obj){
        Field[] field2 = obj.getClass().getDeclaredFields();
        Field[] field = obj.getClass().getFields();
        try{
            for (Field fi : field) {
                String fildname= fi.getName();
                String value = "";
                if(map.get(fildname) != null && map.get(fildname) != ""){
                    value = map.get(fildname).toString();
                }else{
                    continue;
                }

                // 将属性的第一个字母转换为大写
                String frist = fi.getName().substring(0, 1).toUpperCase();
                // 属性封装set方法
                String setter = "set" + frist + fi.getName().substring(1);
                // 获取当前属性类型
                Class<?> type = fi.getType();
                // 获取JavaBean的方法,并设置类型
                Method method = obj.getClass().getMethod(setter, type);
                // 判断属性为double类型
                if ("java.lang.String".equals(type.getName())) {
                    // 调用当前Javabean中set方法，并传入指定类型参数
                    method.invoke(obj, value);
                } else if ("java.lang.Integer".equals(type.getName())) {
                    method.invoke(obj, Integer.parseInt(value));
                }else if ("java.lang.Double".equals(type.getName())) {
                    method.invoke(obj, Double.valueOf(value));
                } else if ("char".equals(type.getName())) {
                    method.invoke(obj, value);
                }else if ("java.util.Date".equals(type.getName())) {
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date startDate = df.parse(value);
                    method.invoke(obj, startDate);
                }else if ("java.lang.Long".equals(type.getName())) {
                    method.invoke(obj, Long.valueOf(value));
                }
            }
        }catch (Exception e){
            e.printStackTrace();

        }
    }

}
