package com.site.mountain.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.GisUtils;
import org.locationtech.jts.geom.Coordinate;

import java.util.Map;

/**
 *  雄安JSON处理
 */
public class StandardXAJsonSliceMethod extends SliceMethod {
    public StandardXAJsonSliceMethod() {
    }


    @Override
    public JSONObject slice() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileType", "json");
        jsonObject.put("fileData", "data");

        /**
         * 步骤：
         * 1、读取原始JSON文件
         * 2、把原始JSON文件结构化
         * 3、根据参数，筛选出来符合的node
         * 4、重新组合成json对象
         * 5、把JSON对象输出到文件中，并发布出去。
         */
        Map<String, String> param = getParam();
        String effectiveRadius = param.get("effectiveRadius") == null ? "0" : param.get("effectiveRadius");
        String lonStr = param.get("lon") == null ? "0" : param.get("lon");
        String latStr = param.get("lat") == null ? "0" : param.get("lat");


        Double lon = Double.valueOf(lonStr);
        Double lat = Double.valueOf(latStr);
        Coordinate c1 = new Coordinate(lon, lat);


        String sourceFilePath = param.get("sourceFilePath");
        String outFilePath = param.get("outFilePath");
//        String sourceFilePath = "D:/项目/地图服务/数据/test.json";
//        String outFilePath = "D:/项目/地图服务/数据/outSlice.json";

        String sourceJsonStr = FileUtils.readText(sourceFilePath);
        JSONObject sourceJson = (JSONObject) JSONObject.parseObject(sourceJsonStr, Feature.OrderedField);
//        JSONObject sourceJson = (JSONObject) JSONObject.parse(sourceJsonStr);
//        JSONObject messageFrame = sourceJson.getJSONObject("MessageFrame");
//        JSONObject mapFrame = messageFrame.getJSONObject("mapFrame");
        JSONObject nodes = sourceJson.getJSONObject("nodes");
        JSONArray nodeList = nodes.getJSONArray("Node");
        JSONObject node = null;
        JSONObject refPos = null;
        Coordinate c2 = new Coordinate();

        JSONArray resultJsonArr = new JSONArray();
        for (int i = 0; i < nodeList.size(); i++) {
            node = nodeList.getJSONObject(i);
            refPos = node.getJSONObject("refPos");
//            Double elevation = refPos.getDouble("elevation");
            Integer latInt = refPos.getInteger("lat");
            Integer nodeLonInt = refPos.getInteger("lon");
//            Integer nodeLonInt = refPos.getInteger("node_lon");
//            Double latDouble = latInt / 10000000.0;
//            Double lonDouble = nodeLonInt / 10000000.0;
            Double lonDouble = refPos.getDouble("lon");
            Double latDouble = refPos.getDouble("lat");
            Double altDouble = refPos.getDouble("alt");
            c2.setX(lonDouble);
            c2.setY(latDouble);
            double pointDistance = GisUtils.getPointDistance(c1, c2);
            if (Double.valueOf(effectiveRadius) >= (pointDistance * 1000)) {
                resultJsonArr.add(nodeList.get(i));
            }
//            System.out.println("node==" + node.toJSONString());
        }
        nodes.put("Node", resultJsonArr);

        String outJsonStr = null;
        try {
            outJsonStr = new ObjectMapper().writeValueAsString(nodes);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        String outJsonStr = JSON.toJSONString(nodes, SerializerFeature.DisableCircularReferenceDetect);
        FileUtils.writeText(outFilePath, outJsonStr, false);

        jsonObject.put("outFilePath", outFilePath);
        return jsonObject;
    }
}
