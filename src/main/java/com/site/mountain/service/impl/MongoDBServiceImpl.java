package com.site.mountain.service.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.site.mountain.mongodb.MongoDBConfigUtil;
import com.site.mountain.service.MongoDBService;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
@Transactional
public class MongoDBServiceImpl implements MongoDBService {
    @Autowired
    MongoDBConfigUtil mongoDBConfigUtil;

    @Override
    public Map<String,Object> findPageList(Map<String, Object> params) {
        String collName = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        int pageStart = 0;
        int pageLimit = 10;
        if (null != params.get("start") && !"".equals(params.get("start"))) {
            pageStart = (Integer) params.get("start");
        }
        if (null != params.get("limit") && !"".equals(params.get("limit"))) {
            pageLimit = (Integer) params.get("limit");
        }
        Document filter = new Document();
        String fileName = "";
        if(!"".equals(params.get("fileName")) && params.get("fileName")!=null){
            fileName = (String) params.get("fileName");
            Pattern pattern = Pattern.compile(fileName+".*$", Pattern.CASE_INSENSITIVE);
            filter.put("fileName",pattern);
        }

        List<Document> reList = new ArrayList<>();
        MongoClient client = mongoDBConfigUtil.getClient();
        MongoCollection collection  = mongoDBConfigUtil.getCollection(client,collName);
        BasicDBObject sort = new BasicDBObject();
        sort.put("serveName", 1);
        sort.put("fileName", 1);
        FindIterable<Document> findIterable = collection.find(filter).skip(pageStart).limit(pageLimit).sort(sort);
        //long count= collection.estimatedDocumentCount();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            Document next = mongoCursor.next();
            next.put("_id",next.getObjectId("_id").toString());
            reList.add(next);
        }
        FindIterable<Document> findIterableAll = collection.find(filter);
        long countAll = 0;
        MongoCursor<Document> mongoCursorAll = findIterableAll.iterator();
        while(mongoCursorAll.hasNext()){
            mongoCursorAll.next();
            countAll++;
        }

        client.close();
        Map<String,Object> reMap = new HashMap<>();
        reMap.put("count",countAll);
        reMap.put("list",reList);
        return reMap;
    }

    @Override
    public List<Document> findList(Map<String, Object> params) {
        String collName = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        List<Document> reList = new ArrayList<>();
        MongoClient client = mongoDBConfigUtil.getClient();
        if(!isExistsCollect(client,collName)){
            client.close();
            return reList;
        }
        MongoCollection collection  = mongoDBConfigUtil.getCollection(client,collName);
        Document filter = new Document();
        FindIterable<Document> findIterable = collection.find(filter);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            Document next = mongoCursor.next();
            next.put("_id",next.getObjectId("_id").toString());
            reList.add(next);
        }
        client.close();
        return reList;
    }

    @Override
    public List<Document> findListParam(Map<String, Object> params,Document filter) {
        String collName = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        List<Document> reList = new ArrayList<>();
        MongoClient client = mongoDBConfigUtil.getClient();
        if(!isExistsCollect(client,collName)){
            client.close();
            return reList;
        }
        MongoCollection collection  = mongoDBConfigUtil.getCollection(client,collName);
        FindIterable<Document> findIterable = collection.find(filter);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            Document next = mongoCursor.next();
            next.put("_id",next.getObjectId("_id").toString());
            reList.add(next);
        }
        client.close();
        return reList;
    }

    @Override
    public void addCollection(Map<String, Object> params) {
        String collName = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        mongoDBConfigUtil.createCollection(client,collName);
        client.close();
    }

    @Override
    public void dropCollection(Map<String, Object> params) {
        String collName = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        MongoCollection collectionOne  = mongoDBConfigUtil.getCollection(client,collName);
        collectionOne.drop();
        client.close();
    }

    @Override
    public List<String> findAllCollections(){
        MongoClient client = mongoDBConfigUtil.getClient();
        List<String> tableList = new ArrayList<>();
        MongoIterable<String> cNameList = mongoDBConfigUtil.getDatabase(client).listCollectionNames();
        MongoCursor<String> cursor = cNameList.iterator();
        while(cursor.hasNext()){
            String table = cursor.next();
            tableList.add(table);
        }
        return tableList;
    }

    @Override
    public void addDocument(Map<String, Object> params) {
        String collName = "";
        Map<String, Object> document = new HashMap<>();
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        if(!"".equals(params.get("document")) && params.get("document")!=null){
            document = (Map<String, Object>) params.get("document");
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        if(!isExistsCollect(client,collName)){
            mongoDBConfigUtil.createCollection(client,collName);
        }
        Document insertDocument = new Document(document);
        MongoCollection collectionOne  = mongoDBConfigUtil.getCollection(client,collName);
        collectionOne.insertOne(insertDocument);
        client.close();
    }

    public boolean isExistsCollect(MongoClient client,String collName){
        boolean boo = false;
        List<String> tableList = new ArrayList<>();
        MongoIterable<String> cNameList = mongoDBConfigUtil.getDatabase(client).listCollectionNames();
        MongoCursor<String> cursor = cNameList.iterator();
        while(cursor.hasNext()){
            String table = cursor.next();
            tableList.add(table);
        }
        if(tableList.contains(collName)){
            boo = true;
        }
        return boo;
    }

    @Override
    public void updateDocument(Map<String, Object> params) {
        String collName = "";
        Map<String, Object> document = new HashMap<>();
        String _id = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }

        if(!"".equals(params.get("document")) && params.get("document")!=null){
            document = (Map<String, Object>) params.get("document");
        }

        if(!"".equals(params.get("_id")) && params.get("_id")!=null){
            _id = (String) params.get("_id");
        }
        if(document.containsKey("_id")){
            document.remove("_id");
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        MongoCollection collectionOne  = mongoDBConfigUtil.getCollection(client,collName);
        Document insertDocument = new Document("$set",document);//collectionOne.deleteOne();
        collectionOne.updateOne(Filters.eq("_id",new ObjectId(_id)),insertDocument);
        client.close();
    }

    @Override
    public long deleteDocument(Map<String, Object> params) {
        String collName = "";
        String _id = "";
        if(!"".equals(params.get("collName")) && params.get("collName")!=null){
            collName = (String) params.get("collName");
        }
        if(!"".equals(params.get("_id")) && params.get("_id")!=null){
            _id = (String) params.get("_id");
        }

        MongoClient client = mongoDBConfigUtil.getClient();
        MongoCollection collectionOne  = mongoDBConfigUtil.getCollection(client,collName);
        DeleteResult re = collectionOne.deleteOne(Filters.eq("_id",new ObjectId(_id)));
        long count = re.getDeletedCount();
        client.close();
        return count;
    }


}
