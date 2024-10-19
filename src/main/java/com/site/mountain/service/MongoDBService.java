package com.site.mountain.service;

import org.bson.Document;

import java.util.List;
import java.util.Map;

public interface MongoDBService {

    Map<String,Object> findPageList(Map<String, Object> params);

    List<Document> findList(Map<String, Object> params);

    List<Document> findListParam(Map<String, Object> params,Document filter);

    void addCollection(Map<String, Object> params);

    void dropCollection(Map<String, Object> params);

    void addDocument(Map<String, Object> params);

    void updateDocument(Map<String, Object> params);

    long deleteDocument(Map<String, Object> params);

    List<String> findAllCollections();
}
