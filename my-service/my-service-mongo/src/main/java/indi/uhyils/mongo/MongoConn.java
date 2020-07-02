package indi.uhyils.mongo;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import indi.uhyils.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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


    public boolean addFile(String fileName, byte[] bytes) {
        GridFSInputFile gridFsInputFile = fs.createFile(bytes);
        gridFsInputFile.setFilename(fileName);
        gridFsInputFile.save();
        close();
        return true;
    }

    public boolean removeFile(String fileName) {
        fs.remove(fileName);
        close();
        return true;
    }

    public List<byte[]> getFile(String fileName) {
        List<GridFSDBFile> gridFSDBFiles = fs.find(fileName);
        List<byte[]> list = new ArrayList<>();
        try {
            for (GridFSDBFile one : gridFSDBFiles) {
                InputStream inputStream = one.getInputStream();
                byte[] b = new byte[inputStream.available()];
                inputStream.read(b);
                inputStream.close();
                list.add(b);

            }
            return list;
        } catch (IOException e) {
            LogUtil.error(this, e);
            return new ArrayList<>();
        } finally {
            close();
        }


    }

}
