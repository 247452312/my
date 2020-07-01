package indi.uhyils.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;

import java.util.Arrays;

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
        MongoCredential credential = MongoCredential.createCredential(mongoConfig.getUsername(), DB_NAME, mongoConfig.getPassword().toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(mongoConfig.getIp(), mongoConfig.getPort()), Arrays.asList(credential));
        DB db = mongoClient.getDB(DB_NAME);
        GridFS fs = new GridFS(db);
        return MongoConn.build(mongoClient, fs, pool);
    }
}
