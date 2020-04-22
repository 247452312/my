package indi.uhyils.model;

import java.io.Serializable;

/**
 * 学生
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时28分
 */
public class User implements Serializable {

    /**
     * 学号
     */
    public Integer id;
    /**
     * 名字
     */
    public String name;
    /**
     * 班级id
     */
    public Integer classId;


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

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
