package indi.uhyils.pojo.model.base;

import indi.uhyils.util.MD5Util;

import java.io.Serializable;
import java.util.UUID;

/**
 * 中间表
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月27日 08时28分
 */
public class MiddleEntity implements Serializable {

    /**
     * 每个中间表都有自己的id
     */
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    public void preInsert() {
        String uuid = UUID.randomUUID().toString();
        this.id = MD5Util.MD5Encode(uuid);
    }
}
