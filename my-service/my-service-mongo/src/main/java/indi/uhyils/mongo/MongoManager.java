package indi.uhyils.mongo;

import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 15时52分
 */
@Component
public class MongoManager {

    @Autowired
    private MongoConnPool pool;

    public boolean addFile(String fileName, byte[] bytes) {
        try {
            MongoConn conn = pool.getConn();
            return conn.addFile(fileName, bytes);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return false;
    }

    public boolean addFile(String fileName, InputStream inputStream) {
        try {
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            return addFile(fileName, b);
        } catch (IOException e) {
            LogUtil.error(this, e);
        }
        return false;

    }

    public boolean removeFile(String fileName) {
        MongoConn conn = null;
        try {
            conn = pool.getConn();
            return conn.removeFile(fileName);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        return false;

    }

    public List<byte[]> getFile(String fileName) {
        MongoConn conn = null;
        try {
            conn = pool.getConn();
            return conn.getFile(fileName);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }

        return null;
    }

}
