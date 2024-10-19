package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.common.SliceMethod;
import com.site.mountain.common.SliceMethodFactory;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.*;
import com.site.mountain.service.*;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.SocketClientUtils;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/map/mecdata")
public class MapMecDataController {
    private static final Logger logger = LoggerFactory.getLogger(MapMecDataController.class);

    @Autowired
    private MapDataService mapDataService;

    @Autowired
    private MapMecService mapMecService;

    @Autowired
    private MapMecDataService mapMecDataService;

    @Autowired
    private MapSectionDataService mapSectionDataService;

    @Autowired
    ConstantProperties constantProperties;

    @Autowired
    private MapAccessService mapAccessService;

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestBody MapMecData mapMecData) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapMecData> pageInfo = mapMecDataService.selectList(mapMecData);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        jsonObject.put("msg", "");
        SliceMethodFactory factory = new SliceMethodFactory();
        if ("xodr".equals(mapData.getDataType())) {
            jsonObject.putAll(processXODR(mapData));
        } else if ("xml".equals(mapData.getDataType())) {
            jsonObject.putAll(processMsgToMapDevice(mapData));
        } else if ("json".equals(mapData.getDataType())) {
            jsonObject.putAll(processMsgToMapDevice(mapData));
        } else if ("xa_json".equals(mapData.getDataType())) {
            jsonObject.putAll(processMsgToMapDevice(mapData));
        }
//        if ("xodr".equals(mapData.getDataType())) {
//            jsonObject.putAll(processXODR(mapData));
//        } else if ("xml".equals(mapData.getDataType())) {
//            jsonObject.putAll(processMsg(mapData));
//        } else if ("json".equals(mapData.getDataType())) {
//            jsonObject.putAll(processMsg(mapData));
//        } else if ("xa_json".equals(mapData.getDataType())) {
//            jsonObject.putAll(processMsg(mapData));
//        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody MapSectionData mapSectionData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        String mainFile = mapSectionData.getFilePath();
        String dataType = mapSectionData.getDataType();
        String fileName = mapSectionData.getSectionName() + "_section_" + mapSectionData.getSectionId();

        String path = constantProperties.getFileUploadPath() + File.separator + dataType;
        FileUtils.mkdirs(path);
        String filePath = path + File.separator + mainFile;

        File file = new File(filePath);

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            if (!file.exists()) {
                logger.info("文件不存在");
                jsonObject.put("code", 20000);
                jsonObject.put("status", 500);
                jsonObject.put("data", "");
                jsonObject.put("msg", "文件不存在");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private JSONObject processXODR(MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String retsultMsg = "";

//        List<MapMec> mecList = mapData.getMapMecList();
        List<MapDevice> mecList = mapData.getMapDeviceList();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        MapMecData mapMecData = null;
        MapDevice tempMapMec = null;
        MapSectionData mapSectionData = null;

        /**
         * 切xodr文件步骤：
         * 1、拷贝原始xodr文件到inputDir
         * 2、创建map.txt文件
         * 3、创建in.txt文件
         * 4、读取切片后文件：读取out.txt文件内容
         * 5、获取切片后的xodr文件，并且拷贝到上传文件夹，并且插入数据库
         */

//        String inputDir = "D:/socket_save/xodr_work/inputDir";
//        String outPutDir = "D:/socket_save/xodr_work/outPutDir";
        String inputDir = constantProperties.getInputDir();
        String outPutDir = constantProperties.getOutPutDir();
        Map<String, MapDevice> mecMap = new HashMap<>();
        // 1、拷贝原始xodr文件到inputDir
        String dataType = mapData.getDataType();
        String mainFile = mapData.getMainFile();
        String dataCode = mapData.getCode();
        String sourceFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + mainFile;

        String targetFolder = inputDir + File.separator + dataCode;
        targetFolder = FileUtils.mkdirs(targetFolder);
        String targetFilePath = targetFolder + File.separator + dataCode + ".xodr";

        FileUtils.copy(sourceFilePath, targetFilePath);

        // 2、创建map.txt文件: circle,35,104.21169372170,30.60989148997,400.0,1 (0平面,1球面)
        String mapPath = inputDir + File.separator + dataCode + File.separator + "map.txt";
        File mapTxtFile = FileUtils.createFile(mapPath);
        FileUtils.writeText(mapPath, "", false);// 清除内容
        for (int i = 0; i < mecList.size(); i++) {
            tempMapMec = mecList.get(i);
            mecMap.put(tempMapMec.getDeviceCode(), tempMapMec);
            // circle,35,6949.07,5615.6,400
            // 0平面，1球面
            String lineTxt = "circle," + tempMapMec.getDeviceCode() + "," +
                    tempMapMec.getLon() + "," + tempMapMec.getLat() + "," + tempMapMec.getEffectiveRadius() + ",0" + "\r\n";
            FileUtils.writeText(mapPath, lineTxt, true);
        }

        // 3、创建in.txt文件
        String inTxtPath = inputDir + File.separator + "in.txt";
        FileUtils.writeText(inTxtPath, dataCode, false);

        // 4、读取切片后文件：读取out.txt文件内容
        String outTxtPath = outPutDir + File.separator + "out.txt";
        FileUtils.createFile(outTxtPath);
        List<String> outTxtList = new ArrayList<>();
        String outTxtLine = "";
        int count = 0;
        while (count <= 50) {
            outTxtList = FileUtils.readLine(outTxtPath);
            if (outTxtList.size() > 0) {
                outTxtLine = outTxtList.get(0);
                break;
            }
            count = count + 1;
            try {
                Thread.sleep(100); //设置暂停的时间 100毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!outTxtLine.equals("")) {

            // 5、获取切片后的xodr文件，并且拷贝到上传文件夹，并且插入数据库
            MapDevice mapMecVal = null;
            MapSectionData insertMapSectionData = null;
            MapMecData insertMapMecData = null;
            for (Map.Entry<String, MapDevice> entry : mecMap.entrySet()) {
                String key = entry.getKey();
                mapMecVal = entry.getValue();
                String filePathStr = key + ".xodr";
                String xodrOutPath = outPutDir + File.separator + outTxtLine + File.separator + filePathStr;
                String xodrTargetPath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + filePathStr;
                FileUtils.copy(xodrOutPath, xodrTargetPath);
                jsonObject.put("outFilePath", xodrTargetPath);

                // 存储下发到MEC切片后的地图
//                String connectURL = mapMecVal.getConnectUrl();
//                boolean result = SocketClientUtils.sendFile(connectURL, xodrOutPath);
//                if (!result) {
//                    retsultMsg = retsultMsg + mapMecVal.getMecName() + ",";
//                } else {
                FileUtils.copy(xodrOutPath, xodrTargetPath);
                String sectionId = UUIDUtil.create32UUID();
                insertMapSectionData = new MapSectionData();
                insertMapSectionData.setDataId(mapData.getDataId());
                insertMapSectionData.setSectionId(sectionId);
                insertMapSectionData.setSectionName(mapData.getDataName());
                insertMapSectionData.setDataType(mapData.getDataType());
                insertMapSectionData.setFilePath(filePathStr);
                insertMapSectionData.setUpdateTime(timestamp);
                insertMapSectionData.setUpdatePerson(sysUser.getUserId().longValue());
                mapSectionDataService.insertSelective(insertMapSectionData);

                insertMapMecData = new MapMecData();
                insertMapMecData.setMecId(mapMecVal.getId());
                insertMapMecData.setSectionId(sectionId);
                insertMapMecData.setStatus(2);
                insertMapMecData.setOptTime(timestamp);
                insertMapMecData.setOptPerson(sysUser.getUserId().longValue());
                mapMecDataService.insertSelective(insertMapMecData);
//                }


            }
            jsonObject.put("status", 200);
        } else {
            jsonObject.put("status", 500);
        }


        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(new File(outTxtPath));
            // 往文件重写内容
            fileWriter.write("");// 清空
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonObject.put("msg", retsultMsg);
        return jsonObject;
    }

    /**
     * 处理消息集（json、xml）
     *
     * @param mapData
     * @return
     */
    private JSONObject processMsg(MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String retsultMsg = "";

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        List<MapMec> mecList = mapData.getMapMecList();

        String dataType = mapData.getDataType();
        String mainFile = mapData.getMainFile();
        String sourceFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + mainFile;
        MapMec tempMapMec;
        SliceMethodFactory factory = new SliceMethodFactory();
        SliceMethod sliceMethod = factory.createSliceMethod(dataType);
//        SliceMethod sliceMethod = factory.createSliceMethod("json");
        Map<String, String> map = new HashMap<>();
        MapSectionData insertMapSectionData = null;
        MapMecData insertMapMecData = null;

        for (int i = 0; i < mecList.size(); i++) {
            tempMapMec = mecList.get(i);
            String filePathStr = tempMapMec.getCode() + "." + dataType;
//            String filePathStr = tempMapMec.getCode() + ".json";
            String outFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + filePathStr;
            Double lonDouble = tempMapMec.getLon() == null ? 0.0 : tempMapMec.getLon();
            Double latDouble = tempMapMec.getLat() == null ? 0.0 : tempMapMec.getLat();
            Float effectiveRadius = tempMapMec.getEffectiveRadius() == null ? 0 : tempMapMec.getEffectiveRadius();
            map.put("sourceFilePath", sourceFilePath);
            map.put("outFilePath", outFilePath);
            map.put("lon", String.valueOf(lonDouble));
            map.put("lat", String.valueOf(latDouble));
            map.put("effectiveRadius", String.valueOf(effectiveRadius));
            sliceMethod.setParam(map);
            jsonObject.put(tempMapMec.getCode(), sliceMethod.slice());
//            jsonObject.putAll(sliceMethod.slice());

            // 存储下发到MEC切片后的地图
//            String connectURL = tempMapMec.getConnectUrl();
//            boolean result = SocketClientUtils.sendFile(connectURL, outFilePath);
//            if (!result) {
//                retsultMsg = retsultMsg + tempMapMec.getMecName() + ",";
//            } else {

            if (sysUser != null) {// 使用用户登录系统，如果没有登录系统，说明是接口调用地图切片，切片后数据不存储到数据库中
                String sectionId = UUIDUtil.create32UUID();
                insertMapSectionData = new MapSectionData();
                insertMapSectionData.setDataId(mapData.getDataId());
                insertMapSectionData.setSectionId(sectionId);
                insertMapSectionData.setSectionName(mapData.getDataName());
                insertMapSectionData.setDataType(mapData.getDataType());
                insertMapSectionData.setFilePath(filePathStr);
                insertMapSectionData.setUpdateTime(timestamp);
                insertMapSectionData.setUpdatePerson(sysUser.getUserId().longValue());
                mapSectionDataService.insertSelective(insertMapSectionData);

                insertMapMecData = new MapMecData();
                insertMapMecData.setMecId(tempMapMec.getMecId());
                insertMapMecData.setSectionId(sectionId);
                insertMapMecData.setStatus(2);
                insertMapMecData.setOptTime(timestamp);
                insertMapMecData.setOptPerson(sysUser.getUserId().longValue());
                mapMecDataService.insertSelective(insertMapMecData);
            }

//            }

        }
        jsonObject.put("status", 200);
        return jsonObject;
    }

    /**
     * 处理消息集（json、xml）
     * 下发到MapDevice的数据
     * @param mapData
     * @return
     */
    private JSONObject processMsgToMapDevice(MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String retsultMsg = "";

        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        List<MapDevice> mecList = mapData.getMapDeviceList();
//        List<MapMec> mecList = mapData.getMapMecList();

        String dataType = mapData.getDataType();
        String mainFile = mapData.getMainFile();
        String sourceFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + mainFile;
        MapDevice tempMapDevice;
        SliceMethodFactory factory = new SliceMethodFactory();
        SliceMethod sliceMethod = factory.createSliceMethod(dataType);
//        SliceMethod sliceMethod = factory.createSliceMethod("json");
        Map<String, String> map = new HashMap<>();
        MapSectionData insertMapSectionData = null;
        MapMecData insertMapMecData = null;

        for (int i = 0; i < mecList.size(); i++) {
            tempMapDevice = mecList.get(i);
            String filePathStr = tempMapDevice.getDeviceCode() + "." + dataType;
//            String filePathStr = tempMapMec.getCode() + ".json";
            String outFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + filePathStr;
            Double lonDouble = tempMapDevice.getLon() == null ? 0.0 : tempMapDevice.getLon();
            Double latDouble = tempMapDevice.getLat() == null ? 0.0 : tempMapDevice.getLat();
            Double effectiveRadius = tempMapDevice.getEffectiveRadius() == null ? 0 : tempMapDevice.getEffectiveRadius();
            map.put("sourceFilePath", sourceFilePath);
            map.put("outFilePath", outFilePath);
            map.put("lon", String.valueOf(lonDouble));
            map.put("lat", String.valueOf(latDouble));
            map.put("effectiveRadius", String.valueOf(effectiveRadius));
            sliceMethod.setParam(map);
            jsonObject.put(tempMapDevice.getDeviceCode(), sliceMethod.slice());
//            jsonObject.putAll(sliceMethod.slice());

            // 存储下发到MEC切片后的地图
//            String connectURL = tempMapMec.getConnectUrl();
//            boolean result = SocketClientUtils.sendFile(connectURL, outFilePath);
//            if (!result) {
//                retsultMsg = retsultMsg + tempMapMec.getMecName() + ",";
//            } else {

            if (sysUser != null) {// 使用用户登录系统，如果没有登录系统，说明是接口调用地图切片，切片后数据不存储到数据库中
                String sectionId = UUIDUtil.create32UUID();
                insertMapSectionData = new MapSectionData();
                insertMapSectionData.setDataId(mapData.getDataId());
                insertMapSectionData.setSectionId(sectionId);
                insertMapSectionData.setSectionName(mapData.getDataName());
                insertMapSectionData.setDataType(mapData.getDataType());
                insertMapSectionData.setFilePath(filePathStr);
                insertMapSectionData.setUpdateTime(timestamp);
                insertMapSectionData.setUpdatePerson(sysUser.getUserId().longValue());
                mapSectionDataService.insertSelective(insertMapSectionData);

                insertMapMecData = new MapMecData();
                insertMapMecData.setMecId(tempMapDevice.getId());
                insertMapMecData.setSectionId(sectionId);
                insertMapMecData.setStatus(2);
                insertMapMecData.setOptTime(timestamp);
                insertMapMecData.setOptPerson(sysUser.getUserId().longValue());
                mapMecDataService.insertSelective(insertMapMecData);
            }

//            }

        }
        jsonObject.put("status", 200);
        return jsonObject;
    }

    private JSONObject sendAndSaveFile(MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String retsultMsg = "";

        String dataType = mapData.getDataType();
        String mainFile = mapData.getMainFile();
        String dataCode = mapData.getCode();
        String sourceFilePath = constantProperties.getFileUploadPath() + File.separator + dataType + File.separator + mainFile;

        // 切片消息
        SliceMethodFactory factory = new SliceMethodFactory();
        SliceMethod sliceMethod = factory.createSliceMethod(mapData.getDataType());
        Map<String, String> map = new HashMap<>();
        map.put("sourceFilePath", sourceFilePath);
        map.put("dataType", dataType);
//        sliceMethod.setParam(map);
//        jsonObject.putAll(sliceMethod.slice());

        List<MapMec> mecList = mapData.getMapMecList();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        MapMecData mapMecData = null;
        MapMec tempMapMec = null;
        MapSectionData mapSectionData = null;
        MapSectionData insertMapSectionData = null;
        MapMecData insertMapMecData = null;
        for (int i = 0; i < mecList.size(); i++) {
            tempMapMec = mecList.get(i);
            String filePathStr = tempMapMec.getCode() + "." + mapData.getDataType();

            String sectionId = UUIDUtil.create32UUID();
            insertMapSectionData = new MapSectionData();
            insertMapSectionData.setDataId(mapData.getDataId());
            insertMapSectionData.setSectionId(sectionId);
            insertMapSectionData.setSectionName(mapData.getDataName());
            insertMapSectionData.setDataType(mapData.getDataType());
            insertMapSectionData.setFilePath(filePathStr);
            insertMapSectionData.setUpdateTime(timestamp);
            insertMapSectionData.setUpdatePerson(sysUser.getUserId().longValue());
            mapSectionDataService.insertSelective(insertMapSectionData);

            insertMapMecData = new MapMecData();
            insertMapMecData.setMecId(tempMapMec.getMecId());
            insertMapMecData.setSectionId(sectionId);
            insertMapMecData.setStatus(2);
            insertMapMecData.setOptTime(timestamp);
            insertMapMecData.setOptPerson(sysUser.getUserId().longValue());
            mapMecDataService.insertSelective(insertMapMecData);
        }


        return jsonObject;
    }


    @RequestMapping(value = "deleteSectionData", method = RequestMethod.POST)
    public void deleteSectionData(@RequestBody List<MapSectionData> list, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);

        MapSectionData msd = null;
        for (int i = 0; i < list.size(); i++) {
            msd = list.get(i);
            int resultInt = mapMecDataService.deleteBySectionId(msd.getSectionId());
            resultInt = mapSectionDataService.deleteBySectionId(msd.getSectionId());
        }

        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "getmap", method = RequestMethod.POST)
    public void getMap(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        jsonObject.put("msg", "");
        MapData processMapData = null;

        String account = mapData.getAccount();
        SysUser sysUser = new SysUser();
        sysUser.setUsername(account);
        SysUser tempUser = sysUserService.findByUser(sysUser);
        if(tempUser == null){
            jsonObject.put("status", 404);
            jsonObject.put("msg", mapData.getAccount() + "账户不合法");
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        List<MapData> mapDataList = mapDataService.selectAllNotPage(mapData);
        if (mapDataList.size() == 0) {
            jsonObject.put("status", 404);
            jsonObject.put("msg", "没有找到" + mapData.getCode() + "地图数据");
            try {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            processMapData = mapDataList.get(0);
            List<MapMec> tempMecList = mapData.getMapMecList();
            MapMec mapMec = null;
            MapMec newMapMec = null;
            List<MapMec> reList = new ArrayList<>();
            for (int i = 0; i < tempMecList.size(); i++) {
                mapMec = tempMecList.get(i);
                newMapMec = mapMecService.selectByPrimaryKey(mapMec);
                if(newMapMec != null){
                    reList.add(newMapMec);
                }

            }
            processMapData.setMapMecList(reList);
//            mapData = tempMapData;
        }

        if ("xodr".equals(processMapData.getDataType())) {
            jsonObject.putAll(processXODR(processMapData));
        } else if ("xml".equals(processMapData.getDataType())) {
            jsonObject.putAll(processMsg(processMapData));
        } else if ("json".equals(processMapData.getDataType())) {
            jsonObject.putAll(processMsg(processMapData));
        } else if ("xa_json".equals(processMapData.getDataType())) {
            jsonObject.putAll(processMsg(processMapData));
        }

        List<MapMec> mapMecs = processMapData.getMapMecList();
        MapMec mapMecTemp = null;
        JSONObject responseJsonObject = new JSONObject();
        JSONObject twoJsonObject = null;
        for (int i = 0; i < mapMecs.size(); i++) {
            twoJsonObject = new JSONObject();
            mapMecTemp = mapMecs.get(i);
            String code = mapMecTemp.getCode();
            JSONObject codeJson = jsonObject.getJSONObject(code);
            String outFilePath = codeJson.getString("outFilePath");
            String sliceJsonContent = FileUtils.readText(outFilePath);
            File file = new File(outFilePath);
            if (!file.exists()) {
                twoJsonObject.put("status", 404);
                twoJsonObject.put("msg", "地图数据获取失败");
                twoJsonObject.put("map", "");
                responseJsonObject.put(code, twoJsonObject);
            } else {
                twoJsonObject.put("status", 200);
                twoJsonObject.put("msg", "地图数据成功");
                twoJsonObject.put("map", JSONObject.parseObject(sliceJsonContent));
                responseJsonObject.put(code, twoJsonObject);
            }
        }

        // 设置访问次数
        MapAccess mapAccess = new MapAccess();
        mapAccess.setMapdataCode(mapData.getCode());
        mapAccess.setAccessAccount(mapData.getAccount());
        MapAccess selectMapAccess = mapAccessService.select(mapAccess);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mapAccess.setOptTime(timestamp);
        int flag = 0;
        if (selectMapAccess == null) {// 新增
            mapAccess.setAccessNum(Long.valueOf("1"));
            flag = mapAccessService.insertSelective(mapAccess);
        } else {//更新
            mapAccess.setAccessNum(selectMapAccess.getAccessNum() + 1);
            flag = mapAccessService.updateByPrimaryKeySelective(mapAccess);
        }

        try {
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(responseJsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 读取切片后的文件
//        String outFilePath = jsonObject.getString("outFilePath");
//
//        File file = new File(outFilePath);
//        FileInputStream fileInputStream = null;
//        BufferedInputStream bufferedInputStream = null;
//        OutputStream outputStream = null;
//
//        response.setHeader("content-type", "application/octet-stream");
//        response.setContentType("application/octet-stream;charset=utf-8");
//        try {
//            if (!file.exists()) {
//                logger.info("文件不存在");
//                jsonObject.put("code", 20000);
//                jsonObject.put("status", 500);
//                jsonObject.put("data", "");
//                jsonObject.put("msg", "切片后地图文件不存在");
//                response.setContentType("text/html;charset=utf-8");
//                response.getWriter().print(jsonObject.toJSONString());
//                return;
//            }
//            String fileName = "download.json";
//            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
//
//            byte[] buffer = new byte[1024];
//            fileInputStream = new FileInputStream(file);
//            bufferedInputStream = new BufferedInputStream(fileInputStream);
//            outputStream = response.getOutputStream();
//            int i = bufferedInputStream.read(buffer);
//            while (i != -1) {
//                outputStream.write(buffer, 0, i);
//                i = bufferedInputStream.read(buffer);
//            }
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//                if (bufferedInputStream != null) {
//                    bufferedInputStream.close();
//                }
//                if (fileInputStream != null) {
//                    fileInputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }


    }

    @RequestMapping(value = "getmap2", method = RequestMethod.POST)
    public void getMapByJson(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        jsonObject.put("status", 200);
        jsonObject.put("msg", "");
        if ("xodr".equals(mapData.getDataType())) {
            jsonObject.putAll(processXODR(mapData));
        } else if ("xml".equals(mapData.getDataType())) {
            jsonObject.putAll(processMsg(mapData));
        } else if ("json".equals(mapData.getDataType())) {
            jsonObject.putAll(processMsg(mapData));
        }

        // 读取切片后的文件
        String outFilePath = jsonObject.getString("outFilePath");
        String readText = FileUtils.readText(outFilePath);
        jsonObject.put("content", readText);
        try {
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
