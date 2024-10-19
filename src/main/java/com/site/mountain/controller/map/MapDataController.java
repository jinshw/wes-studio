package com.site.mountain.controller.map;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.entity.*;
import com.site.mountain.server.SocketClient;
import com.site.mountain.service.MapDataObjService;
import com.site.mountain.service.MapDataService;
import com.site.mountain.utils.FileUtils;
import com.site.mountain.utils.UUIDUtil;
import com.site.mountain.utils.XmlUtils;
import com.site.mountain.utils.ZipUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/map/data")
public class MapDataController {
    private static final Logger logger = LoggerFactory.getLogger(MapDataController.class);

    @Autowired
    private MapDataService mapDataService;

    @Autowired
    private MapDataObjService mapDataObjService;

    @Autowired
    ConstantProperties constantProperties;

    /**
     * 查询数据服务列表
     *
     * @param mapData
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestBody MapData mapData) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapData> pageInfo = mapDataService.selectAll(mapData);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        jsonObject.put("fileUrl",constantProperties.getTilesUri());
        return jsonObject;
    }

    @RequestMapping(value = "mecDataList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject mecDataList(@RequestBody MapMecData mapMecData) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapSectionData> pageInfo = mapDataService.selectMecDataList(mapMecData);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    /**
     * 查询服务对应的数据列表
     *
     * @param mapDataObj
     * @return
     */
    @RequestMapping(value = "serviceDataList", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject serviceDataList(@RequestBody MapDataObj mapDataObj) {
        JSONObject jsonObject = new JSONObject();
        PageInfo<MapDataObj> pageInfo = mapDataObjService.selectAll(mapDataObj);
        jsonObject.put("status", 200);
        jsonObject.put("code", 20000);
        jsonObject.put("number", pageInfo.getTotal());
        jsonObject.put("data", pageInfo);
        return jsonObject;
    }

    /**
     * 新增加地图数据
     *
     * @param mapData
     * @param request
     * @param response
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public void add(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mapData.setUpdateTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapData.setUpdatePerson(sysUser.getUserId().longValue());
        }

        // 判断code是否存在
        MapData tempMapData = new MapData();
        tempMapData.setCode(mapData.getCode());
        PageInfo<MapData> mapDataPageInfo = mapDataService.selectAll(tempMapData);
        if (mapDataPageInfo.getList().size() > 0) {
            jsonObject.put("status", 501);
            jsonObject.put("msg", "code已经存在");
        } else {
//            if (mapData.getDataType().equals("geojson")) {
//                mapDataService.insertMongoDB(mapData);
//            }
            int flag = mapDataService.add(mapData);
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
     *
     * @param mapData
     * @param request
     * @param response
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);

        Long reNum = mapDataService.getRelationList(mapData.getDataId());
        if (reNum > 0) {
            jsonObject.put("status", 600);
        } else {
            int flag = mapDataService.delete(mapData.getDataId());
            if (flag > 0) {
                // 删除MongoDB关联数据
//                Map<String, Object> tempMap = new HashMap<>();
//                tempMap.put("code", mapData.getCode());
//                mapDataService.deleteDocumentByFilters("data-server", tempMap);
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
     * 更新数据信息
     *
     * @param mapData
     * @param request
     * @param response
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void update(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mapData.setUpdateTime(timestamp);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            mapData.setUpdatePerson(sysUser.getUserId().longValue());
        }
//        if (mapData.getDataType().equals("geojson")) {
//            mapDataService.insertMongoDB(mapData);
//        }
        int flag = mapDataService.updateByPrimaryKeySelective(mapData);
        mapDataService.xodrToObj(mapData);
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
        String dataId = request.getParameter("dataId");
        String dataType = request.getParameter("dataType");

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
            // 获取系统时间
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            // 获取系统当前用户
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            MapData mapData = new MapData();
            mapData.setDataId(Long.parseLong(dataId));
            mapData.setPath(pathName + "/" + fPathName);
            mapData.setUpdateTime(timestamp);
            mapData.setUpdatePerson(sysUser.getUserId().longValue());
            int flag = mapDataService.updateByPrimaryKeySelective(mapData);
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            } else {
                // 解压zip压缩文件

                boolean unzipFlag = ZipUtils.unzip(filePath, path + pathName);
                if (unzipFlag) {// 解压成功，读取关系文件
                    if (dataType.equals("obj")) {
                        String mainFilePath = path + relativePath + "/" + "map.txt";
                        processObj(mainFilePath, mapData);
                    } else if (dataType.equals("shape")) {
                        String mainPath = path + relativePath;
                        processShape(mainPath, mapData);
                    } else if (dataType.equals("gltf")) {
                        String mainPath = path + relativePath;
                        processGltf(mainPath, mapData);
                    }

                }
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
     * 获取上传文件的内容，并且返回给前端
     *
     * @param file
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "fileUploadGetContent", method = RequestMethod.POST)
    public void fileUploadGetContent(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String dataType = request.getParameter("dataType");

        SysFiles sysFiles = getSysFiles(file);
        //获取文件名
        String filePathName = UUIDUtil.create32UUID() + sysFiles.getSuffixName();
        String path = constantProperties.getFileUploadPath() + File.separator + dataType;
        FileUtils.mkdirs(path);
        String filePath = path + File.separator + filePathName;
        File saveFile = new File(filePath);

        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            if (dataType.equals("geojson")) {
                jsonObject.put("data", processGeojson(saveFile));
            } else {
                jsonObject.put("data", "");
            }
            jsonObject.put("filePathName", filePathName);
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody MapData mapData, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
//        String fileName = sysFiles.getFname();
//        String filePath = sysFiles.getPath();
//        String path = constantProperties.getFileUploadPath() + filePath;

        String mainFile = mapData.getMainFile();
        String dataType = mapData.getDataType();
        String fileName = mapData.getCode();

        String path = constantProperties.getFileUploadPath() + dataType;
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


    /**
     * Obj类型文件处理
     *
     * @param mainFilePath
     * @param mapData
     */
    public void processObj(String mainFilePath, MapData mapData) {
        List<String> readList = FileUtils.readLine(mainFilePath);
        System.out.println(readList);
        MapDataObj mapDataObj = null;
        String lineStr = "";
        for (int i = 0; i < readList.size(); i++) {
            lineStr = readList.get(i);
            if (!lineStr.equals("")) {
                String[] splitLine = lineStr.split(",");
                mapDataObj = new MapDataObj();
                mapDataObj.setSheetNumber(splitLine[0]);
                mapDataObj.setX(splitLine[1]);
                mapDataObj.setY(splitLine[2]);
                mapDataObj.setZ(splitLine[3]);
                mapDataObj.setDataId(mapData.getDataId());
                mapDataObj.setUpdateTime(mapData.getUpdateTime());
                mapDataObj.setUpdatePerson(mapData.getUpdatePerson());
                // 把关系文件的图幅信息存储到数据库
                mapDataObjService.insert(mapDataObj);
            }
        }
    }

    /**
     * 处理shape文件
     *
     * @param path
     * @param mapData
     */
    public void processShape(String path, MapData mapData) {
        File file = new File(path);
        String mainFile = "";
        if (file.exists()) {
            if (null == file.listFiles()) {
                return;
            }
            File[] files = file.listFiles();
            File tempFile = null;
            for (int i = 0; i < files.length; i++) {

                tempFile = files[i];
                //获取文件的原始名称
                String originalFilename = tempFile.getName();
                //获取最后一个.的位置
                int lastIndexOf = tempFile.getName().lastIndexOf(".");
                //获取文件的后缀名
                String suffix = originalFilename.substring(lastIndexOf);
                if (".shp".equals(suffix)) {
                    mainFile = originalFilename;
                }

            }
            mapData.setMainFile(mainFile);
            mapDataService.updateByPrimaryKeySelective(mapData);
//            mapDataService.insertMongoDB(mapData);
        }
    }

    /**
     * 读取GeoJson内容
     *
     * @param geojsonFile
     * @return
     * @throws IOException
     */
    public String processGeojson(File geojsonFile) throws IOException {
//        FileReader fileReader = new FileReader(geojsonFile);
        Reader reader = new InputStreamReader(new FileInputStream(geojsonFile), "utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
//        fileReader.close();
        reader.close();
        geojsonFile.delete();
        String geojsonStr = sb.toString();
        return geojsonStr;
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
     * 处理上传的GLTF
     *
     * @param path
     * @param mapData
     */
    public void processGltf(String path, MapData mapData) throws IOException, JDOMException, ParserConfigurationException, SAXException {
        System.out.println(path);
        File file = new File(path);
        File[] files = file.listFiles();
        File gltfFile, xmlFile;
        JSONObject gltfmarkJson, mongodbJson;
        for (File tempFile : files) {
            String fileName = tempFile.getName();
            if (tempFile.isDirectory()) {
                System.out.println(fileName);
                String gltfPath = path + "/" + fileName + "/" + fileName + ".gltf";
                String xmlPath = path + "/" + fileName + "/" + fileName + ".xml";

                gltfFile = new File(gltfPath);
                xmlFile = new File(xmlPath);
                boolean isExistGltf = gltfFile.exists();
                boolean isExistXml = xmlFile.exists();
                // gltf和xml文件必须都存在
                if (isExistGltf && isExistXml) {
                    // 读取gltf内容为json
//                    Reader reader = new InputStreamReader(new FileInputStream(gltfFile));
//                    int ch = 0;
//                    StringBuffer sb = new StringBuffer();
//                    while ((ch = reader.read()) != -1) {
//                        sb.append((char) ch);
//                    }
//                    reader.close();
//                    String gltfStr = sb.toString();
//                    JSONObject gltfJson = JSONObject.parseObject(gltfStr);

                    // 读取xml中坐标
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder;
                    dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);
                    DOMBuilder domBuilder = new DOMBuilder();
                    org.jdom2.Document xmlDoc = domBuilder.build(doc);
                    Element rootElement = xmlDoc.getRootElement();
                    List<Element> children = rootElement.getChildren();
                    Element element = children.get(0);
                    Attribute center_x = element.getAttribute("center_X");
                    Attribute center_y = element.getAttribute("center_Y");
                    String center_xValue = center_x.getValue();
                    String center_yValue = center_y.getValue();

                    // 写入MongoDB中
//                    Float xFlaot = Float.valueOf(center_xValue);
//                    Float yFloat = Float.valueOf(center_yValue);
//                    System.out.println("xFlaot=" + String.valueOf(xFlaot) + " -- yFloat=" + String.valueOf(yFloat));
//                    gltfmarkJson = new JSONObject();
//                    gltfmarkJson.put("x", xFlaot);
//                    gltfmarkJson.put("y", yFloat);
//                    mongodbJson = new JSONObject();
////                    mongodbJson.put("gltf",gltfJson);
//                    mongodbJson.put("mark",gltfmarkJson);
//                    mongodbJson.put("code",mapData.getCode());
//                    mongodbJson.put("dataId",mapData.getDataId());
//                    mongodbJson.put("dataType",mapData.getDataType());
//                    mongodbJson.put("sheetNum",fileName);

//                    mapDataService.insertGltfMongoDB(mongodbJson);

                    // 写入Mysql中
                    MapDataObj mapDataObj = new MapDataObj();
                    mapDataObj.setDataId(mapData.getDataId());
                    mapDataObj.setSheetNumber(fileName);
                    mapDataObj.setX(center_xValue);
                    mapDataObj.setY(center_yValue);
                    mapDataObj.setUpdatePerson(mapData.getUpdatePerson());
                    mapDataObj.setUpdateTime(mapData.getUpdateTime());
                    mapDataObjService.insert(mapDataObj);
                }
            }
        }


        System.out.println(mapData);
    }


}
