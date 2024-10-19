package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysDict;
import com.site.mountain.entity.SysDistrict;
import com.site.mountain.service.SysDictService;
import com.site.mountain.service.SysDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author XU
 * @Title:
 * @Package
 * @Description: 全国市县坐标
 * @date 2021/5/17 001714:35
 */
@Controller
@RequestMapping("/sysDistrict")
public class SysDistrictCortroller {
    @Autowired
    public SysDistrictService sysDistrictService;

    @RequestMapping(value = "queryCityList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject queryCityList(@RequestBody SysDistrict sysDistrict, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        List list = sysDistrictService.queryCityList(sysDistrict);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        jsonObject.put("msg", "ok");
        return jsonObject;
    }




}
