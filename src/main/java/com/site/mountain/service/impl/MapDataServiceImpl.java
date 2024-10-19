package com.site.mountain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.dao.mysql.MapDataMapper;
import com.site.mountain.dll.GenerateObjFromXodr;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.entity.MapSectionData;
import com.site.mountain.mongodb.MongoDBConfigUtil;
import com.site.mountain.service.MapDataService;
import com.site.mountain.service.MongoDBService;
import com.site.mountain.utils.FileUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

@Service
public class MapDataServiceImpl implements MapDataService {

    @Autowired
    private MapDataMapper mapDataMapper;

    @Autowired
    MongoDBConfigUtil mongoDBConfigUtil;

    @Autowired
    MongoDBService mongoDBService;

    @Autowired
    ConstantProperties constantProperties;

    @Override
    public PageInfo<MapData> selectAll(MapData mapData) {
        PageHelper.startPage(mapData.getPageNum(), mapData.getPageSize());
        List<MapData> list = mapDataMapper.selectAll(mapData);
        MapData tempMapData = null;
        String collName = "data-server";
        List<Document> documentList = null;
        Document document;
        Map<String, Object> map = new HashMap();
        Map<String, Object> filterMap = new HashMap();
        map.put("collName", collName);
//        for (int i = 0; i < list.size(); i++) {
//            tempMapData = list.get(i);
//            filterMap.put("code", tempMapData.getCode());
//            Document filter = new Document(filterMap);
//            documentList = findListParam(collName, filter);
//            if (documentList.size() > 0) {
//                document = documentList.get(0);
//                tempMapData.setContent(document.getString("content"));
//                list.set(i, tempMapData);
//            }
//        }
        PageInfo<MapData> pageInfo = new PageInfo(list, mapData.getPageSize());
        return pageInfo;
    }

    public PageInfo<MapSectionData> selectMecDataList(MapMecData mapMecData) {
        PageHelper.startPage(mapMecData.getPageNum(), mapMecData.getPageSize());
        List<MapSectionData> list = mapDataMapper.selectMecDataList(mapMecData);
        PageInfo<MapSectionData> pageInfo = new PageInfo(list, mapMecData.getPageSize());
        return pageInfo;
    }

    public List<MapData> selectAllNotPage(MapData mapData) {
        return mapDataMapper.selectAll(mapData);
    }

    public int add(MapData mapData) {
        return mapDataMapper.insert(mapData);
    }

    public int delete(Long dataId) {
        return mapDataMapper.deleteByPrimaryKey(dataId);
    }

    @Override
    public Long getRelationList(Long dataId) {
        List<Map<String, Object>> relationList = mapDataMapper.selectRelation(dataId);
        Long reNum = 0L;
        for (int i = 0; i < relationList.size(); i++) {
            Map<String, Object> tempMap = relationList.get(i);
            reNum += Long.parseLong(tempMap.get("num").toString());
        }
        return reNum;
    }

    public int updateByPrimaryKeySelective(MapData mapData) {
        return mapDataMapper.updateByPrimaryKeySelective(mapData);
    }

    /**
     * 把geojson数据插入MongoDB
     *
     * @param mapData
     */
    public void insertMongoDB(MapData mapData) {
        String collName = "data-server";
        Map<String, Object> map = new HashMap();
        Map<String, Object> filterMap = new HashMap();
        map.put("collName", collName);

        filterMap.put("code", mapData.getCode());
        Document filter = new Document(filterMap);
        List<Document> list = findListParam(collName, filter);

        Map<String, Object> delParams = new HashMap<>();
        Document tempDocument = null;

        for (int i = 0; i < list.size(); i++) {
            tempDocument = list.get(i);
            delParams.put("collName", collName);
            delParams.put("_id", tempDocument.getString("_id"));
            mongoDBService.deleteDocument(delParams);
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        if (!isExistsCollect(client, collName)) {
            mongoDBConfigUtil.createCollection(client, collName);
        }
        Document insertDocument = Document.parse(JSONObject.toJSONString(mapData));
        MongoCollection collectionOne = mongoDBConfigUtil.getCollection(client, collName);
        collectionOne.insertOne(insertDocument);
        client.close();
    }

    /**
     * 把gltf数据插入MongoDB中
     *
     * @param jsonObject
     */
    public void insertGltfMongoDB(JSONObject jsonObject) {
        String collName = "data-server-gltf";
        // 图幅号
        String sheetNum = jsonObject.getString("sheetNum");
        Map<String, Object> map = new HashMap();
        Map<String, Object> filterMap = new HashMap();
        map.put("collName", collName);

        filterMap.put("sheetNum", sheetNum);
        Document filter = new Document(filterMap);
        List<Document> list = findListParam(collName, filter);

        Map<String, Object> delParams = new HashMap<>();
        Document tempDocument = null;

        for (int i = 0; i < list.size(); i++) {
            tempDocument = list.get(i);
            delParams.put("collName", collName);
            delParams.put("_id", tempDocument.getString("_id"));
            mongoDBService.deleteDocument(delParams);
        }

//        MongoClient client = mongoDBConfigUtil.getClient();
//        if (!isExistsCollect(client, collName)) {
//            mongoDBConfigUtil.createCollection(client, collName);
//        }
//        Document insertDocument = Document.parse(JSONObject.toJSONString(jsonObject));
//        MongoCollection collectionOne = mongoDBConfigUtil.getCollection(client, collName);
//        collectionOne.insertOne(insertDocument);
//        client.close();


//        MongoClient client = mongoDBConfigUtil.getClient();
//        GridFSBucket gridFSBucket = mongoDBConfigUtil.getgridFSBucket(client);

        String str = JSONObject.toJSONString(jsonObject);
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());

        MongoClient client = mongoDBConfigUtil.getClient();
        GridFSBucket gridFSBucket = mongoDBConfigUtil.getGridFSBucket(client);
        gridFSBucket.find(Filters.eq("filename", sheetNum)).forEach(
                new Consumer<GridFSFile>() {
                    @Override
                    public void accept(final GridFSFile gridFSFile) {
                        gridFSBucket.delete(gridFSFile.getObjectId());
                        System.out.println(gridFSFile.getFilename());
                    }
                });
        // 保存文件到fs中
        ObjectId objectId = gridFSBucket.uploadFromStream(sheetNum, inputStream);
        client.close();

    }

    /**
     * 根据文件名获取文件内容
     *
     * @param fileName
     * @return
     */
    public String getGridFsContentByFileName(String fileName) {
        String result = "";
        MongoClient client = mongoDBConfigUtil.getClient();
        GridFSBucket gridFSBucket = mongoDBConfigUtil.getGridFSBucket(client);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(fileName, baos);
        try {
            result = baos.toString("UTF-8");
        } catch (Exception e) {
            result = "";
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 根据文件id获取文件内容
     *
     * @param objectId
     * @return
     */
    public String getGridFsContentByObjectId(String objectId) {
        String result = "";
        MongoClient client = mongoDBConfigUtil.getClient();
        GridFSBucket gridFSBucket = mongoDBConfigUtil.getGridFSBucket(client);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(new ObjectId(objectId), baos);
        try {
            result = baos.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = "";
            e.printStackTrace();
        }
        return result;
    }


    public boolean isExistsCollect(MongoClient client, String collName) {
        boolean boo = false;
        List<String> tableList = new ArrayList<>();
        MongoIterable<String> cNameList = mongoDBConfigUtil.getDatabase(client).listCollectionNames();
        MongoCursor<String> cursor = cNameList.iterator();
        while (cursor.hasNext()) {
            String table = cursor.next();
            tableList.add(table);
        }
        if (tableList.contains(collName)) {
            boo = true;
        }
        return boo;
    }

    /**
     * 删除MongoDB数据
     *
     * @param collName
     * @param filterMap
     * @return
     */
    public long deleteDocumentByFilters(String collName, Map<String, Object> filterMap) {
        Document document = new Document(filterMap);
        MongoClient client = mongoDBConfigUtil.getClient();
        MongoCollection collectionOne = mongoDBConfigUtil.getCollection(client, collName);
        DeleteResult re = collectionOne.deleteMany(document);
        long count = re.getDeletedCount();
        client.close();
        return count;
    }

    /**
     * MongoDB数据查询
     *
     * @param collName
     * @param filter
     * @return
     */
    public List<Document> findListParam(String collName, Document filter) {
        List<Document> reList = new ArrayList<>();
        MongoClient client = mongoDBConfigUtil.getClient();
        if (!isExistsCollect(client, collName)) {
            client.close();
            return reList;
        }
        MongoCollection collection = mongoDBConfigUtil.getCollection(client, collName);
        FindIterable<Document> findIterable = collection.find(filter);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            Document next = mongoCursor.next();
            next.put("_id", next.getObjectId("_id").toString());
            reList.add(next);
        }
        client.close();
        return reList;
    }

    @Async
    public void xodrToObj(MapData mapData) {
        String fileUploadPath = constantProperties.getFileUploadPath();
        String xodrFile = fileUploadPath + File.separator + mapData.getDataType() + File.separator + mapData.getMainFile();
        String fileName = mapData.getCode();
        String objRelativePath = mapData.getDataType() + "/" + "obj" + "/" + mapData.getCode() + "/" + mapData.getCode();
        String objOutFolderPath = fileUploadPath + "/" + objRelativePath;
        if (FileUtils.isDir(objOutFolderPath)) {
            FileUtils.deleteFolder(new File(objOutFolderPath));
        }
        FileUtils.mkdirs(objOutFolderPath);

//        String filePath = "C:\\Users\\Administrator\\Desktop\\obj_test\\xodr_out\\test1211.xodr";
//        String fileName = "test1211";
//        String outFolderPath = "C:\\Users\\Administrator\\Desktop\\obj_test\\xodr_out\\out";
        String userDir = System.getProperty("user.dir");
        String strtextName = userDir + "/dll/texture";

//        String outLonLat = GenerateObjFromXodr.generateObjFromXodr.GenerateObjModelByXodr(filePath,fileName,outFolderPath,strtextName);
        String outLonLat = GenerateObjFromXodr.generateObjFromXodr.GenerateObjModelByXodr(xodrFile, fileName, objOutFolderPath, strtextName);

        if (outLonLat != null) {
//            String[] lonlatStr = outLonLat.split("\n");
            String[] splitLonLat = outLonLat.split(",");
            String lonStr = splitLonLat[0];
            String latStr = splitLonLat[1];
            Double lon = Double.valueOf(lonStr);
            Double lat = Double.valueOf(latStr);
            mapData.setObjLon(lon);
            mapData.setObjLat(lat);
            mapData.setObjPath(objRelativePath);

            mapDataMapper.updateByPrimaryKeySelective(mapData);

            // obj转3dtiles
            objTo3dtiles(mapData);
        }
    }

    @Async
    public void objTo3dtiles(MapData mapData) {
        DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerTlsVerify(false)
                // 这里填最上面填的ip端口号，ip换成服务器ip
                .withDockerHost(constantProperties.getDockerHost())
//                .withDockerHost("tcp://localhost:2375")
                // docker API版本号，可以用docker version查看
                .withApiVersion(constantProperties.getDockerApiVersion())
                // 默认
                .withRegistryUrl("https://index.docker.io/v1/").build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .sslConfig(config.getSSLConfig())
                .maxConnections(10000)
                .connectionTimeout(Duration.ofSeconds(60 * 20))
                .responseTimeout(Duration.ofSeconds(60 * 30))
                .build();
//
        DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);


        // 容器名称
        String containerName = constantProperties.getContainerName();

        // 获取容器id
        String containerId = null;
        List<Container> containers = dockerClient.listContainersCmd().exec();
        for (Container container : containers) {
            String[] names = container.getNames();
            if (container.getNames()[0].equals("/" + containerName)) {
                containerId = container.getId();
                break;
            }

        }

        // 输出容器id
//        if (containerId != null) {
//            System.out.println("容器名称：" + containerName);
//            System.out.println("容器id：" + containerId);
//        } else {
//            System.out.println("未找到名为" + containerName + "的容器");
//        }

        String hostBasePath = constantProperties.getHostBasePath();
        String dockerBasePath = constantProperties.getDockerBasePath();
        String jsonParam = constantProperties.getJsonParam();
//        String hostBasePath = "D:/obj23dtiles/data/";
//        String dockerBasePath = "/APP/data/";
//        String jsonParam = "/APP/data/semanticyizhuang.json";
        String relativeInputPath = "obj/xodr/obj/" + mapData.getCode() + "/";
        String relativeOutpuPath = "obj/xodr/3dtiles/" + mapData.getCode() + "/";
//        String relativeInputPath = "/yizhuangOBJ/";
//        String relativeOutpuPath = "/yizhuangOBJ_result/";
        String inputPath = dockerBasePath + relativeInputPath;
        String outputPath = dockerBasePath + relativeOutpuPath;
        String hostOutputPath = hostBasePath + "obj/xodr/3dtiles/";
        if (FileUtils.isDir(hostOutputPath)) {
            File file = new File(hostOutputPath);
            FileUtils.deleteFolder(file);
        }
        FileUtils.mkdirs(hostOutputPath);

        ExecCreateCmdResponse response = dockerClient.execCreateCmd(containerId)
//        ExecCreateCmdResponse response = dockerClient.execCreateCmd("c863b407b73e49bf069b377ea5fa76952ca986a0c1f72d3e1e512d486f1eaca5")
//                .withCmd("bash", "-c","ls","-l")
                .withEnv(Arrays.asList("PATH=/root/.nvm/versions/node/v16.13.0/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"))
                .withCmd("python3", "/APP/software/obj23dtiles/batch_process.py", "-i", inputPath, "-o", outputPath, "-z", "3", "-r", "0.005", "-f", "Y", "-u", "Z", "-w", "6", "-j", jsonParam, "-e")
                .withAttachStdout(true)
                .withAttachStderr(true)
//                .withCmd(commands)
//                .withCmd( "./usr/bin/python3", "/APP/software/obj23dtiles/batch_process.py", "-i", "/APP/data/yizhuangOBJ/", "-o", "/APP/data/yizhuangOBJ_result/", "-z", "3", "-r", "0.005", "-f", "Y", "-u", "Z", "-w", "6", "-j", "/APP/data/semanticyizhuang.json", "-e")
                .exec();
        ExecStartCmd startCmd = dockerClient.execStartCmd(response.getId());
        try {
            startCmd.exec(new ExecStartResultCallback(System.out, System.err) {
                @Override
                public void onComplete() {
                    System.out.println("3dtiles更新到数据库...");
                    String tilesPath = relativeOutpuPath + mapData.getCode() + "/" + "tileset.json";
                    mapData.setTilesPath(tilesPath);
                    mapDataMapper.updateByPrimaryKeySelective(mapData);
                    super.onComplete();
                }
            }).awaitCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
