package com.site.mountain.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.MapData;
import com.site.mountain.entity.MapMecData;
import com.site.mountain.entity.MapSectionData;
import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface MapDataService {
    PageInfo<MapData> selectAll(MapData mapData);

    PageInfo<MapSectionData> selectMecDataList(MapMecData mapMecData);

    List<MapData> selectAllNotPage(MapData mapData);


    int add(MapData mapData);

    int delete(Long dataId);

    Long getRelationList(Long dataId);

    int updateByPrimaryKeySelective(MapData mapData);

    void insertMongoDB(MapData mapData);

    /**
     * 把gltf数据插入MongoDB中
     *
     * @param jsonObject
     */
    void insertGltfMongoDB(JSONObject jsonObject);


    long deleteDocumentByFilters(String collName, Map<String, Object> filterMap);

    List<Document> findListParam(String collName, Document filter);

    String getGridFsContentByFileName(String fileName);

    String getGridFsContentByObjectId(String objectId);

    void xodrToObj(MapData mapData);

}
