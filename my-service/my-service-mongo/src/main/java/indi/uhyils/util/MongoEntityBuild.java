package indi.uhyils.util;

import indi.uhyils.pojo.model.MongoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 16时47分
 */
public class MongoEntityBuild {

    public static ArrayList<MongoEntity> build(String name, List<byte[]> file) {
        ArrayList<MongoEntity> list = new ArrayList<>();
        for (byte[] bytes : file) {
            MongoEntity mongoEntity = new MongoEntity();
            mongoEntity.setFileName(name);
            mongoEntity.setBytes(bytes);
            list.add(mongoEntity);
        }
        return list;
    }

}
