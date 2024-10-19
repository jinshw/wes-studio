package com.site.mountain.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SysDict;
import com.site.mountain.service.SysDictService;
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
 * @Description: 项目字典管理
 * @date 2021/5/17 00179:54
 */
@Controller
@RequestMapping("/sysDictPj")
public class SysDictCortroller {

    @Autowired
    public SysDictService sysDictService;

    @RequestMapping(value = "selectDictByCode", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(@RequestBody SysDict sysDict, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        List list = sysDictService.findList(sysDict);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        jsonObject.put("msg", "ok");
        return jsonObject;
    }

}
