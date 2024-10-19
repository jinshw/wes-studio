package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.*;
import com.site.mountain.service.Map3dtilesService;
import com.site.mountain.service.MapDataObjService;
import com.site.mountain.service.MapDataService;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.UUIDUtil;
import com.site.mountain.utils.ZipUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map/3dtiles")
public class Map3dtilesController {
    @Autowired
    private Map3dtilesService map3dtilesService;


    @Autowired
    ConstantProperties constantProperties;

    /**
     * 查询3dtiles服务列表
     *
     * @param map3dtiles
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestBody Map3dtiles map3dtiles) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<Map3dtiles> pageInfo = map3dtilesService.selectAll(map3dtiles);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("fileUrl", constantProperties.getFileUrl());
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    /**
     * 增加3dtiles
     *
     * @param map3dtiles
     * @param request
     * @param response
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody Map3dtiles map3dtiles, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        map3dtiles.setUpdateTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            map3dtiles.setUpdatePerson(sysUser.getUserId().longValue());
        }

        // 判断code是否存在
        Map3dtiles tempObj = new Map3dtiles();
        tempObj.setTilesCode(map3dtiles.getTilesCode());
        PageInfo<Map3dtiles> pageInfo = map3dtilesService.selectAll(tempObj);
        if (pageInfo.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "code已经存在");
        } else {
            int flag = map3dtilesService.add(map3dtiles);
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

    /**
     * 删除数据
     * @param map3dtiles
     * @param request
     * @param response
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody Map3dtiles map3dtiles, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        int flag = map3dtilesService.delete(map3dtiles);
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

    /**
     * 更新数据信息
     * @param map3dtiles
     * @param request
     * @param response
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody Map3dtiles map3dtiles, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        map3dtiles.setUpdateTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            map3dtiles.setUpdatePerson(sysUser.getUserId().longValue());
        }
        int flag = map3dtilesService.update(map3dtiles);
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

    /**
     * 文件上传（zip文件）
     *
     * @param file
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String tilesId = request.getParameter("tilesId");
        String tilesType = request.getParameter("tilesType");

        SysFiles sysFiles = getSysFiles(file);
        //获取文件名
        String filePathName = UUIDUtil.create32UUID() + sysFiles.getSuffixName();
//        String fileName = file.getOriginalFilename();
        String path = constantProperties.getFileUploadPath();
        String filePath = path + filePathName;
        File saveFile = new File(filePath);
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + filePathName;
            jsonObject.put("name", filePathName);
            jsonObject.put("url", coverUrl);
            sysFiles.setPath(filePathName);
            String fPathName = sysFiles.getFname().replace(sysFiles.getSuffixName(), "");
            String pathName = sysFiles.getPath().replace(sysFiles.getSuffixName(), "");
            String relativePath = pathName + "/" + fPathName;
            String uri = relativePath +"/" + "tileset.json";
            // 获取系统时间
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            // 获取系统当前用户
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            Map3dtiles map3dtiles = new Map3dtiles();
            map3dtiles.setTilesId(Long.parseLong(tilesId));
            map3dtiles.setUri(uri);
            map3dtiles.setUpdateTime(timestamp);
            map3dtiles.setUpdatePerson(sysUser.getUserId().longValue());

            boolean unzipFlag = ZipUtils.unzip(filePath, path + pathName);
            if (unzipFlag) {// 解压成功
                int flag = map3dtilesService.update(map3dtiles);
                if (flag == 0) {
                    jsonObject.put("status", 501);
                    jsonObject.put("msg", "文件上传失败");
                }
            }else{
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件信息
     *
     * @param file
     * @return
     */
    public SysFiles getSysFiles(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        long size = file.getSize();
        SysFiles sysFiles = new SysFiles();
        sysFiles.setFname(fileName);
        sysFiles.setSuffixName(suffix);
        sysFiles.setSize(size);
        return sysFiles;
    }




}
