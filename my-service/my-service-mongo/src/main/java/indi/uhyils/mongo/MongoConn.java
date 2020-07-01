package indi.uhyils.mongo;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;

/**
 * mongo工厂生产的东西
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月01日 14时53分
 */
public class MongoConn {

    /**
     * 客户端连接,用来连接客户端
     */
    private MongoClient mongoClient;

    /**
     * 用来存取文件
     */
    private GridFS fs;

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

    public GridFS getFs() {
        return fs;
    }

    public void setFs(GridFS fs) {
        this.fs = fs;
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

    public static MongoConn build(MongoClient mongoClient, GridFS gridFS, MongoConnPool pool) {
        MongoConn build = new MongoConn();
        build.fs = gridFS;
        build.mongoClient = mongoClient;
        build.pool = pool;
        build.createTime = System.currentTimeMillis();
        build.haveUse = false;
        return build;
    }

    public void close() {
        if (pool == null) {
            mongoClient.close();
        } else {
            pool.returnConn(this);
        }
    }
}
