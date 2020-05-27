package indi.uhyils.pojo.model;

import java.io.Serializable;

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
}
