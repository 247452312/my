package indi.uhyils.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * mongo
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 14时28分
 */
public class MongoDbFactory {

    private MongoConfig mongoConfig;
    /**
     * 存放文件的数据库
     */
    private static final String DB_NAME = "fileDB";



    private static MongoDbFactory INSTANCE = null;

    private MongoDbFactory(MongoConfig mongoConfig) {
        this.mongoConfig = mongoConfig;
    }

    /**
     * 双重检测所 单例
     *
     * @param mongoConfig
     * @return
     */
    public static MongoDbFactory getInstance(MongoConfig mongoConfig) {
        if (INSTANCE == null) {
            synchronized (MongoDbFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MongoDbFactory(mongoConfig);
                }
            }
            return INSTANCE;
        }
        return INSTANCE;
    }

    public MongoConn getConn(MongoConnPool pool) {

        MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
        //每个地址的最大连接数
        builder.connectionsPerHost(10);
        MongoCredential credential = MongoCredential.createCredential(mongoConfig.getUsername(), DB_NAME, mongoConfig.getPassword().toCharArray());
        ServerAddress address = new ServerAddress(mongoConfig.getIp(), mongoConfig.getPort());
        MongoClient mongoClient = new MongoClient(address, credential, builder.build());
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        MongoCollection<Document> collection = database.getCollection(DB_NAME);
        return MongoConn.build(mongoClient, collection, pool);
    }
}
