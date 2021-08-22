package indi.uhyils.utils;

import indi.uhyils.pojo.model.MongoDO;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月02日 16时47分
 */
public class MongoDOBuild {

    public static MongoDO build(String name, byte[] file) {
        MongoDO mongoDO = new MongoDO();
        mongoDO.setFileName(name);
        mongoDO.setBytes(file);
        return mongoDO;
    }

}
