package indi.uhyils.mongo;

import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 15时52分
 */
@Component
public class MongoManager {

    @Autowired
    private MongoConnPool pool;

    public boolean addFile(String fileName, String base) {
        try {
            MongoConn conn = pool.getConn();
            return conn.addFile(fileName, base.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
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

    public String getFile(String fileName) {
        MongoConn conn = null;
        try {
            conn = pool.getConn();
            byte[] file = conn.getFile(fileName);
            return new String(file, StandardCharsets.UTF_8);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }

        return null;
    }

}
