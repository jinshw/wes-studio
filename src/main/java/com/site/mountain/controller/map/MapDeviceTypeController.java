package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapDeviceType;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapDeviceTypeService;
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
import java.util.List;

@Controller
@RequestMapping("/map/devicetype")
public class MapDeviceTypeController {

    @Autowired
    private MapDeviceTypeService mapDeviceTypeService;

    @RequestMapping(value = "listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectListByPage(@RequestBody MapDeviceType mapDeviceType,
                                       HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapDeviceType> pageInfo = mapDeviceTypeService.selectListByPage(mapDeviceType);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectList(@RequestBody MapDeviceType mapDeviceType,
                                 HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        List<MapDeviceType> list = mapDeviceTypeService.selectList(mapDeviceType);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapDeviceType mapDeviceType,
                    HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapDeviceType.setOptPerson(sysUser.getUserId().longValue());
        }

        // 判断是否存在
        MapDeviceType tempMapDeviceType = new MapDeviceType();
        tempMapDeviceType.setCode(mapDeviceType.getCode());
        PageInfo<MapDeviceType> page = mapDeviceTypeService.selectListByPage(tempMapDeviceType);
        if (page.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "code已经存在");
        } else {
            mapDeviceType.setOptTime(timestamp);
            mapDeviceType.setCreatetime(timestamp);
            mapDeviceType.setIsDel("0");
            int flag = mapDeviceTypeService.insert(mapDeviceType);
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


    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody MapDeviceType mapDeviceType, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapDeviceType.setOptPerson(sysUser.getUserId().longValue());
            mapDeviceType.setOptTime(timestamp);
        }

        int flag = mapDeviceTypeService.updateByPrimaryKeySelective(mapDeviceType);
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

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody MapDeviceType mapDeviceType, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        MapDeviceType temp = new MapDeviceType();
        temp.setDtId(mapDeviceType.getDtId());
        temp.setIsDel("1");

        List<MapDeviceType> mapDeviceTypes = mapDeviceTypeService.selectRelation(temp);
        if(mapDeviceTypes.size() > 0){
            jsonObject.put("status", 500);
            jsonObject.put("msg","数据正在使用，不能删除");
        }else{
            int result = mapDeviceTypeService.updateByPrimaryKeySelective(temp);
            if (result > 0) {
                jsonObject.put("status", 200);
            } else {
                jsonObject.put("status", 500);
            }
        }

        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
