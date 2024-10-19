package com.site.mountain.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConfigUtil {


    @Value("${spring.data.mongodb.url}")
    public String Mongodb_Url;

    @Value("${spring.data.mongodb.port}")
    public int Mongodb_Port;

    @Value("${spring.data.mongodb.database}")
    public String Mongodb_Database;

    @Value("${spring.data.mongodb.username}")
    public String Mongodb_Username;

    @Value("${spring.data.mongodb.password}")
    public String Mongodb_Password;

    /* static {
        if (client==null){
            MongoClientOptions.Builder builder=new MongoClientOptions.Builder();
            builder.connectionsPerHost(10);//每个地址的最大连接数
            builder.connectTimeout(5000);//连接超时时间
            builder.socketTimeout(5000);//设置读写操作超时时间
            MongoCredential credential=MongoCredential.createCredential(Mongodb_Username, "hams_Mongdb", Mongodb_Password);//user--database--password
            ServerAddress address = new ServerAddress("127.0.0.1", 27017);
            client = new MongoClient(address,credential,builder.build());
        }
    }*/

    public MongoClient getClient() {
        MongoClient client = null;
        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        builder.connectionsPerHost(10);//每个地址的最大连接数
        builder.connectTimeout(5000);//连接超时时间
        builder.socketTimeout(5000);//设置读写操作超时时间
        MongoCredential credential = MongoCredential.createCredential(Mongodb_Username, Mongodb_Database, Mongodb_Password.toCharArray());//user--database--password
        ServerAddress address = new ServerAddress(Mongodb_Url, Mongodb_Port);
        client = new MongoClient(address, credential, builder.build());
        return client;
    }

    public MongoDatabase getDatabase(String dbName) {
        MongoClient client = getClient();
        return client.getDatabase(dbName);
    }

    public MongoDatabase getDatabase(MongoClient client) {
        return client.getDatabase(Mongodb_Database);
    }

    public MongoCollection getCollection(MongoClient client, String collName) {
        return getDatabase(client, Mongodb_Database).getCollection(collName);
    }

    public void createCollection(MongoClient client, String collName) {
        getDatabase(client, Mongodb_Database).createCollection(collName);
    }

    public void createCollection(MongoClient client, MongoCollection collection) {
        collection.drop();
    }

    //-----------------------------------------------
    public MongoDatabase getDatabase(MongoClient client, String dbName) {
        return client.getDatabase(dbName);
    }

    public MongoCollection getCollection(MongoClient client, String dbName, String collName) {
        return getDatabase(client, dbName).getCollection(collName);
    }

    //获取MongoDB中的集合
    public MongoCollection getCollection(MongoDatabase mongoDatabase, String collName) {
        return mongoDatabase.getCollection(collName);
    }

    public MongoCollection getCollection(String dbName, String collName) {
        MongoDatabase mongoDatabase = getDatabase(dbName);
        return mongoDatabase.getCollection(collName);
    }


    //创建集合
    public void createCollection(MongoDatabase mongoDatabase, String collName) {
        mongoDatabase.createCollection(collName);
        System.out.println("集合创建成功");
    }

    //删除集合
    public void dropCollection(MongoCollection coll) {
        coll.drop();
    }

    public GridFSBucket getGridFSBucket(MongoClient mongoClient) {
        MongoDatabase mongoDatabase = getDatabase(mongoClient);
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDatabase);
        return gridFSBucket;
    }
}
