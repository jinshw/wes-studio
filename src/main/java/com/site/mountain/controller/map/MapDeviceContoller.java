package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.MapDevice;
import com.site.mountain.entity.SysFiles;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.MapDeviceService;
import com.site.mountain.utils.ResponseUtils;
import com.site.mountain.utils.UUIDUtil;
import com.site.mountain.utils.ZipUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("/map/device")
public class MapDeviceContoller {

    @Autowired
    private MapDeviceService mapDeviceService;

    @Autowired
    ConstantProperties constantProperties;

    @RequestMapping(value = "listByPage", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectListByPage(@RequestBody MapDevice mapDevice,
                                       HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapDevice> pageInfo = mapDeviceService.selectListByPage(mapDevice);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        String path = constantProperties.getFileUrl();
        jsonObject.put("fileURL", path);
        return jsonObject;
    }

    @RequestMapping(value = "selectGltfList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject selectGltfList(@RequestBody MapDevice mapDevice,
                               HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        List<MapDevice> list = mapDeviceService.selectGltfList(mapDevice);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("list", list);
        String path = constantProperties.getFileUrl();
        jsonObject.put("fileURL", path);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapDevice mapDevice,
                    HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapDevice.setOptPerson(sysUser.getUserId().longValue());
        }

        // 判断是否存在
        MapDevice tempMapDevice = new MapDevice();
        tempMapDevice.setDeviceCode(mapDevice.getDeviceCode());
        PageInfo<MapDevice> page = mapDeviceService.selectListByPage(tempMapDevice);
        if (page.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "deviceCode已经存在");
        } else {
            mapDevice.setOptTime(timestamp);
            mapDevice.setCreateTime(timestamp);
            mapDevice.setIsDel("0");
            mapDevice.setStatus("1");
            int flag = mapDeviceService.insertSelective(mapDevice);
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
    public void update(@RequestBody MapDevice mapDevice, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapDevice.setOptPerson(sysUser.getUserId().longValue());
            mapDevice.setOptTime(timestamp);
        }

        int flag = mapDeviceService.updateByPrimaryKeySelective(mapDevice);
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
    public void delete(@RequestBody MapDevice mapDevice, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);

        MapDevice temp = new MapDevice();
        temp.setId(mapDevice.getId());
        temp.setIsDel("1");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            temp.setOptPerson(sysUser.getUserId().longValue());
            temp.setOptTime(timestamp);
        }

        int result = mapDeviceService.updateByPrimaryKeySelective(temp);
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
    public void deleteList(@RequestBody List<MapDevice> list, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        int result = mapDeviceService.deleteList(list);
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

    @RequestMapping(value = "export", method = RequestMethod.POST)
    public ResponseEntity export(@RequestBody MapDevice mapDevice,
                                 HttpServletRequest request, HttpServletResponse response) {

        return mapDeviceService.export(mapDevice);
    }

    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    public void fileUpload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String id = request.getParameter("id");
        String deviceType = request.getParameter("deviceType");

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
            String uri = relativePath + "/" + fPathName + ".obj";
            // 获取系统时间
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            // 获取系统当前用户
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            MapDevice mapDevice = new MapDevice();
            mapDevice.setId(Long.parseLong(id));
            mapDevice.setObjPath(uri);
            mapDevice.setOptTime(timestamp);
            mapDevice.setOptPerson(sysUser.getUserId().longValue());
//            Map3dtiles map3dtiles = new Map3dtiles();
//            map3dtiles.setTilesId(Long.parseLong(tilesId));
//            map3dtiles.setUri(uri);
//            map3dtiles.setUpdateTime(timestamp);
//            map3dtiles.setUpdatePerson(sysUser.getUserId().longValue());


            boolean unzipFlag = ZipUtils.unzip(filePath, path + pathName);
            if (unzipFlag) {// 解压成功

                boolean resultBool = obj2gltf(path + relativePath, fPathName);
                mapDevice.setGltfPath(relativePath + "/" + fPathName + ".gltf");
                if (resultBool) {
                    int flag = mapDeviceService.updateByPrimaryKeySelective(mapDevice);
                    if (flag == 0) {
                        jsonObject.put("status", 501);
                        jsonObject.put("msg", "文件上传失败");
                    }
                }

            } else {
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

    /**
     * obj转gltf模型
     *
     * @param path
     * @param fileName
     * @return 部署系统中必须安装nodejs环境，并且安装obj2gltf
     */
    public boolean obj2gltf(String path, String fileName) {
        boolean resultBool = true;
        String objFilePath = path + "/" + fileName + ".obj";
        String gltfFilePath = path + "/" + fileName + ".gltf";
        String command = "obj2gltf -i " + objFilePath + " -o " + gltfFilePath;
        try {
            // 执行命令行程序
            Process process = Runtime.getRuntime().exec(command);

            // 获取命令行程序的输出结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待命令行程序执行完毕
            int exitCode = process.waitFor();
            System.out.println("命令行程序执行完毕，退出码：" + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            resultBool = false;
        }

        return resultBool;
    }

    @ApiOperation(value = "查询数据", notes = "查询数据")
    @RequestMapping(value = "/getDeviceListByDeviceType", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtils getDeviceListByDeviceType(@RequestBody MapDevice record) {
        record.setPageSize(1000);
        List<MapDevice> dataList = mapDeviceService.selectListByDeviceType(record);
        for(MapDevice device:dataList){
            String path = constantProperties.getFileUrl();
            device.setFileUrl(path+"/"+device.getGltfPath());
        }
        return ResponseUtils.success(dataList.size(),dataList);
    }

}
