package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapDataObj;
import com.site.mountain.service.MapApiService;
import com.site.mountain.service.MapDataObjService;
import com.site.mountain.service.MapDataService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/map/api")
@CrossOrigin
public class MapApiController {

    @Autowired
    private MapDataService mapDataService;

    @Autowired
    private MapDataObjService mapDataObjService;

    @Autowired
    private MapApiService mapApiService;

    @Autowired
    ConstantProperties constantProperties;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getList(@RequestBody MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        List<MapData> list = mapDataService.selectAllNotPage(mapData);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "getObj", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getObjByXYDistance(@RequestBody MapDataObj mapDataObj) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        try {
            MapData mapData = new MapData();
            mapData.setCode(mapDataObj.getCode());
            List<MapData> list = mapDataService.selectAllNotPage(mapData);
            MapData mapDataMain = null;
            if (list.size() > 0) {
                mapDataMain = list.get(0);
                List<MapDataObj> objByXYDistance = mapApiService.getObjByXYDistance(mapDataMain, mapDataObj);
                jsonObject.put("url", constantProperties.getFileUrl() + "/" + mapDataMain.getPath());
                jsonObject.put("data", objByXYDistance);
            }

        } catch (Exception e) {
            jsonObject.put("status", 500);
        }

        return jsonObject;
    }

    @RequestMapping(value = "getGeojson", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getGeojson(@RequestBody MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        try {
            Document filter = Document.parse(JSONObject.toJSONString(mapData));
            List<Document> list = mapDataService.findListParam("data-server", filter);
            jsonObject.put("data", list);
        } catch (Exception e) {
            jsonObject.put("status", 500);
        }

        return jsonObject;
    }

    @RequestMapping(value = "getGltf", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getGltf(@RequestBody List<String> sheetNos) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);

        try {
            String sheetNo = "";
            String contentStr = "";
            List<JSONObject> list = new ArrayList<>();
            for (int i = 0; i < sheetNos.size(); i++) {
                sheetNo = sheetNos.get(i);
                contentStr = mapDataService.getGridFsContentByFileName(sheetNo);
                list.add(JSONObject.parseObject(contentStr));
            }
            jsonObject.put("data", list);
        } catch (Exception e) {
            jsonObject.put("status", 500);
        }

        return jsonObject;
    }
}
