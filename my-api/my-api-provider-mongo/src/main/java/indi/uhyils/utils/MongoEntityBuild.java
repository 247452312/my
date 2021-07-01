package indi.uhyils.utils;

import indi.uhyils.pojo.model.MongoEntity;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 16时47分
 */
public class MongoEntityBuild {

    public static MongoEntity build(String name, byte[] file) {
        MongoEntity mongoEntity = new MongoEntity();
        mongoEntity.setFileName(name);
        mongoEntity.setBytes(file);
        return mongoEntity;
    }

}
