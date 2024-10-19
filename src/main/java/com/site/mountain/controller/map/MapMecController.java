package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.controller.base.BaseController;
import com.site.mountain.dll.MyDll;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapMec;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapMecDataService;
import com.site.mountain.service.MapMecService;
import com.sun.jna.Platform;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map/mec")
public class MapMecController extends BaseController {

    @Autowired
    private MapMecService mapMecService;
    @Autowired
    private MapMecDataService mapMecDataService;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestBody MapMec mapMec) {
        JSONObject jsonObject = new JSONObject();
        List<SysUser> userList = this.getUserList();
        mapMec.setUserList(userList);
        PageInfo<MapMec> pageInfo = mapMecService.selectList(mapMec);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    @RequestMapping(value = "mapdatalist", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject mapdatalist(@RequestBody MapMec mapMec) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapMecData> pageInfo = mapMecService.mapdatalist(mapMec);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapMec mapMec, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mapMec.setOptTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapMec.setOptPerson(sysUser.getUserId().longValue());
        }

        // 判断code是否存在
        MapMec tempMapMec = new MapMec();
        tempMapMec.setCode(mapMec.getCode());
        PageInfo<MapMec> mapMecPageInfo = mapMecService.selectList(tempMapMec);
        if (mapMecPageInfo.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "code已经存在");
        } else {
            int flag = mapMecService.add(mapMec);
            if (flag > 0) {
                jsonObject.put("status", 200);
            } else {
                jsonObject.put("status", 500);
            }
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody MapMec mapMec, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        MapMecData mapMecData = new MapMecData();
        mapMecData.setMecId(mapMec.getMecId());
//        List<MapMecData> list = mapMecDataService.selectList(mapMecData);
//        if (list.size() > 0) {
//            jsonObject.put("status", 600);
//        } else {
//            int flag = mapDataService.delete(mapData.getDataId());
//        }
        mapMecDataService.delete(mapMecData);
        mapMecService.delete(mapMec.getMecId());
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody MapMec mapMec, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mapMec.setOptTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapMec.setOptPerson(sysUser.getUserId().longValue());
        }
        int flag = mapMecService.updateByPrimaryKeySelective(mapMec);
        if (flag > 0) {
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
