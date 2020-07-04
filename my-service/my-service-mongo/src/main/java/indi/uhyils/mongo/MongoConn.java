package indi.uhyils.mongo;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * mongo工厂生产的东西
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 14时53分
 */
public class MongoConn {


    /**
     * key
     */
    private static final String KEY_NAME = "key";

    /**
     * value
     */
    private static final String VALUE_NAME = "value";

    /**
     * 客户端连接,用来连接客户端
     */
    private MongoClient mongoClient;

    /**
     * 用来存取文件
     */
    private MongoCollection<Document> mongoCollection;

    /**
     * 是否正在被使用
     */
    private Boolean haveUse;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * mongo连接池
     */
    private MongoConnPool pool;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoCollection<Document> getMongoCollection() {
        return mongoCollection;
    }

    public void setMongoCollection(MongoCollection<Document> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public Boolean getHaveUse() {
        return haveUse;
    }

    public void setHaveUse(Boolean haveUse) {
        this.haveUse = haveUse;
    }

    public MongoConnPool getPool() {
        return pool;
    }

    public void setPool(MongoConnPool pool) {
        this.pool = pool;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public static MongoConn build(MongoClient mongoClient, MongoCollection<Document> mongoCollection, MongoConnPool pool) {
        MongoConn build = new MongoConn();
        build.mongoCollection = mongoCollection;
        build.mongoClient = mongoClient;
        build.pool = pool;
        build.createTime = System.currentTimeMillis();
        build.haveUse = false;
        return build;
    }

    /**
     * 关闭连接, 如果在线程池中,则归还连接,不关闭
     */
    public void close() {
        if (pool == null) {
            mongoClient.close();
        } else {
            pool.returnConn(this);
        }
    }


    public boolean addFile(String fileName, String base) {
        Document document = new Document();
        document.put(KEY_NAME, fileName);
        document.put(VALUE_NAME, base);
        mongoCollection.insertOne(document);
        close();
        return true;
    }

    public boolean removeFile(String fileName) {
        Bson bson = Filters.gte(KEY_NAME, fileName);
        mongoCollection.deleteOne(bson);
        close();
        return true;
    }

    public String getFile(String fileName) {
        try {
            Bson name = Filters.eq(KEY_NAME, fileName);
            FindIterable<Document> documents = mongoCollection.find(name);
            MongoCursor<Document> iterator = documents.iterator();
            if (iterator.hasNext()) {
                Document next = iterator.next();
                String s = next.toJson();
                JSONObject jsonObject = JSONObject.parseObject(s);
                Object o = jsonObject.get(VALUE_NAME);
                return o == null ? null : o.toString();
            }
            return null;
        } finally {
            close();
        }
    }

}
