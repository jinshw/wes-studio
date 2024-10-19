package com.site.mountain.controller.cms;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.CmsColumn;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.CmsColumnService;
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
@RequestMapping("cmsColumn")
public class CmsColumnController {

    @Autowired
    private CmsColumnService cmsColumnService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void insert(@RequestBody CmsColumn cmsColumn, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cmsColumn.setCreatetime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        cmsColumn.setOptPerson(sysUser.getUserId());
        int flag = cmsColumnService.insert(cmsColumn);
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

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody CmsColumn cmsColumn, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cmsColumn.setUpdatetime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        cmsColumn.setOptPerson(sysUser.getUserId());
        int flag = cmsColumnService.updateOne(cmsColumn);
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

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findList(@RequestBody CmsColumn cmsColumn) {
        JSONObject jsonObject = new JSONObject();
        List<CmsColumn> list = cmsColumnService.findList(cmsColumn);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }

    @RequestMapping(value = "treeList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findTreeList(@RequestBody CmsColumn cmsColumn) {
        JSONObject jsonObject = new JSONObject();
        List<CmsColumn> list = cmsColumnService.findListByCode(cmsColumn);
        jsonObject.put("code", 20000);
        jsonObject.put("data", list);
        return jsonObject;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody CmsColumn cmsColumn, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        int flag = cmsColumnService.delete(cmsColumn);
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
