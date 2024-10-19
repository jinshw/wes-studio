package com.site.mountain.common;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SliceTest {
    public static void main(String[] args) {
        SliceMethodFactory factory = new SliceMethodFactory();
//        SliceMethod sliceMethod = factory.createSliceMethod("json");
//        Map<String, String> map = new HashMap<>();
//        map.put("lon", "92.4819020463424");
//        map.put("lat", "43.2509190028098");
//        map.put("effectiveRadius", "70");
//        sliceMethod.setParam(map);
//        JSONObject jsonObject = sliceMethod.slice();
//        System.out.println("slice result:" + jsonObject.toJSONString());

        SliceMethod sliceMethod = factory.createSliceMethod("xml");
        sliceMethod.slice();
    }
}
