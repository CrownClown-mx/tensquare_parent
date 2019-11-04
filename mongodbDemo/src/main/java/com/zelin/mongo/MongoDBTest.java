package com.zelin.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MongoDBTest {
    //1.查询所有记录
    @Test
    public void test01() {
        //1.1得到MongoCollection集合
        MongoCollection<Document> mongoCollection = getMongoCollection();
        //1.2遍历集合
        FindIterable<Document> documents = mongoCollection.find();
        //1.3打印信息
        printInfo(documents);
    }
    //2.根据条件查询
    @Test
    public void test02(){
        //2.1得到MongoCollection集合
        MongoCollection<Document> mongoCollection = getMongoCollection();
        //2.2封装查询条件
        BasicDBObject bson = new BasicDBObject("userid","1012");
        //2.3根据条件查询结果
        FindIterable<Document> documents = mongoCollection.find(bson);
        //打印
        printInfo(documents);
    }
    //3.查询浏览量大于1000的记录
    @Test
    public void test03(){
        //3.1得到MongoCollection集合
        MongoCollection<Document> mongoCollection = getMongoCollection();
        //3.2构造查询条件
        BasicDBObject bson = new BasicDBObject("visits",new BasicDBObject("$gt",99));
        //3.3根据条件查询结果
        FindIterable<Document> documents = mongoCollection.find(bson);
        //打印
        printInfo(documents);
    }

    //4.添加单条记录
    @Test
    public void test04(){
        //4.1得到MongoCollection集合
        MongoCollection<Document> mongoCollection = getMongoCollection();
        //4.2定义一个要添加的数据map集合
        Map<String,Object> map = new HashMap<>();
        map.put("id","10");
        map.put("content","欢迎晴明大佬");
        map.put("nickname","晴明");
        map.put("visits",2000);
        map.put("userid","1010");
        //将map与document进行绑定
        Document doc = new Document(map);
        mongoCollection.insertOne(doc);
    }

    private void printInfo(FindIterable<Document> documents) {
        for (Document document : documents) {
            //输出文档对象内容
            Object id = document.get("_id");
            String content =document.getString("content");
            String userid = document.getString("userid");
            String nickname = document.getString("nickname");
            Integer visits = document.getInteger("visits");
            System.out.println("id:" + id);
            System.out.println("content:" + content);
            System.out.println("userid:" + userid);
            System.out.println("nickname:" + nickname);
            System.out.println("visits:" + visits);
            System.out.println("---------------------------------------------");
        }
    }

    //1.得到MongoCollection
    private MongoCollection<Document> getMongoCollection() {
        //1.1得到MongoClient对象
        MongoClient mongoClient = new MongoClient("192.168.25.130");
        //1.2通过客户端得到数据库
        MongoDatabase spit = mongoClient.getDatabase("spitdb");
        //1.3通过数据库得到集合
        MongoCollection<org.bson.Document> spitdb = spit.getCollection("spit");
        //1.4返回
        return spitdb;
    }
}
