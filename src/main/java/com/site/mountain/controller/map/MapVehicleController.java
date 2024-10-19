package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.MapVehicle;
import com.site.mountain.entity.SysFiles;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapVehicleService;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/map/vehicle")
public class MapVehicleController {

    @Autowired
    private MapVehicleService mapVehicleService;
    @Autowired
    ConstantProperties constantProperties;


    @RequestMapping(value = "listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectListByPage(@RequestBody MapVehicle mapVehicle,
                                       HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapVehicle> pageInfo = mapVehicleService.selectListByPage(mapVehicle);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        String fileUrl = constantProperties.getFileUrl();
        jsonObject.put("fileUrl", fileUrl);
        return jsonObject;
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapVehicle mapVehicle,
                    HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapVehicle.setOptPerson(sysUser.getUserId().longValue());
        }

        // 判断是否存在
        MapVehicle tempMapVehicle = new MapVehicle();
        tempMapVehicle.setVehicleNumber(mapVehicle.getVehicleNumber());
        PageInfo<MapVehicle> page = mapVehicleService.selectListByPage(tempMapVehicle);
        if (page.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "车牌号已经存在");
        } else {
            mapVehicle.setOptTime(timestamp);
            mapVehicle.setCreateTime(timestamp);
            mapVehicle.setIsDel("1");
            int flag = mapVehicleService.insertSelective(mapVehicle);
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
    public void update(@RequestBody MapVehicle mapVehicle, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapVehicle.setOptPerson(sysUser.getUserId().longValue());
            mapVehicle.setOptTime(timestamp);
        }

        int flag = mapVehicleService.updateByPrimaryKeySelective(mapVehicle);
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
    public void delete(@RequestBody MapVehicle mapVehicle, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);

        MapVehicle temp = new MapVehicle();
        temp.setVehicleId(mapVehicle.getVehicleId());
        temp.setIsDel("2");// 是否删除：1正常，2删除

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            temp.setOptPerson(sysUser.getUserId().longValue());
            temp.setOptTime(timestamp);
        }

        int result = mapVehicleService.updateByPrimaryKeySelective(temp);
        if (result > 0) {
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

    @RequestMapping(value = "deleteList", method = RequestMethod.POST)
    public void deleteList(@RequestBody List<MapVehicle> list, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        int result = mapVehicleService.deleteList(list);
        if (result > 0) {
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

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);


        SysFiles sysFiles = getSysFiles(file);
        String fileType = sysFiles.getSuffixName().replace(".", "");
        String filePathName = UUIDUtil.create32UUID() + sysFiles.getSuffixName();
        String path = constantProperties.getFileUploadPath() + File.separator + fileType;

        FileUtils.mkdirs(path);
        String filePath = path + File.separator + filePathName;
        String imgPath = fileType + File.separator + filePathName;
//        String imgUrl = constantProperties.getFileUrl() + File.separator + fileType + File.separator + filePathName;

        File saveFile = new File(filePath);
        boolean isCreateSuccess = false;
        try {
            isCreateSuccess = saveFile.createNewFile();
            if (isCreateSuccess) {
                //写入文件
                file.transferTo(saveFile);
                jsonObject.put("status", 200);
                jsonObject.put("imgName", filePathName);
                jsonObject.put("imgPath", imgPath);
            } else {
                jsonObject.put("status", 500);
                jsonObject.put("msg", "文件已存在");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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


    @RequestMapping(value = "export",method = RequestMethod.POST)
    public ResponseEntity export(@RequestBody MapVehicle mapVehicle,
                                 HttpServletRequest request, HttpServletResponse response) {

        return mapVehicleService.export(mapVehicle);
    }


}
