package com.site.mountain.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.AppAccident;
import com.site.mountain.service.AppAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("appAccident")
public class AppAccidentController {

    @Autowired
    private AppAccidentService appAccidentService;

    /**
     * 事故原因分析
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "sgyyfx", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject sgyyfx(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectSGYYFX(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 事故形态分类：事故形态
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "sgxt", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectSGXT(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectSGXT(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 事故形态分类：车辆间事故
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "cljsg", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectCLJSG(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectCLJSG(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 事故形态分类：单车事故
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "dcsg", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectDCSG(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectDCSG(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 事故车辆品牌排名
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "sgclpppm", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectSGCLPPPM(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectSGCLPPPM(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 省份事故排名
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "sfsgpm", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectSFSGPM(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectSFSGPM(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     * 车辆行驶状态
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "xszt", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectXSZT(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        List<AppAccident> list = appAccidentService.selectXSZT(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        return jsonObject;
    }

    /**
     *  Map事故分布
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "sgfb", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectSGFB(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        Map<String,List<AppAccident>> map = appAccidentService.selectSGFB(appAccident);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("data", map);
        return jsonObject;
    }

    /**
     *  事故24小时分布
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "24h", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject select24H(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();

        List<String> intervalList = new ArrayList<>();
        intervalList.add("0-08");
        intervalList.add("08-16");
        intervalList.add("16-24");
        String startDateStr = appAccident.getStartDateStr();
        String year = startDateStr.split("-")[0];

        Map<String, List> map = appAccidentService.select24H(year,intervalList);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("data", map);
        return jsonObject;
    }

    /**
     * 总事故起数
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "selectTotal", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectTotal(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        Integer total = appAccidentService.selectTotal();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("total", total);
        return jsonObject;
    }
    /**
     * 肇事起数
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "selectZSQS", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectZSQS(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        Integer total = appAccidentService.selectZSQS();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("value", total);
        return jsonObject;
    }

    /**
     * 死亡人数
     *
     * @param appAccident
     * @return
     */
    @RequestMapping(value = "selectSWRS", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectSWRS(@RequestBody AppAccident appAccident) {
        JSONObject jsonObject = new JSONObject();
        Integer total = appAccidentService.selectSWRS();
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("value", total);
        return jsonObject;
    }

}
