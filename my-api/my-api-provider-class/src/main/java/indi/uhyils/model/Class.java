package indi.uhyils.model;

import java.io.Serializable;

/**
 * 班级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时32分
 */
public class Class implements Serializable {

    /**
     * 班级号
     */
    public Integer id;

    /**
     * 班级名称
     */
    public String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
