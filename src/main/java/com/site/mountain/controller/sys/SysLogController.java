package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.MapVehicleAlarm;
import com.site.mountain.entity.SysLog;
import com.site.mountain.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/syslog")
public class SysLogController {


    @Autowired
    private SysLogService sysLogService;

    @RequestMapping(value = "listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectListByPage(@RequestBody SysLog sysLog,
                                       HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<SysLog> pageInfo = sysLogService.selectListByPage(sysLog);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    @RequestMapping(value = "export",method = RequestMethod.POST)
    public ResponseEntity export(@RequestBody SysLog sysLog,
                                 HttpServletRequest request, HttpServletResponse response) {

        return sysLogService.export(sysLog);
    }
}
