package indi.uhyils.model;



/**
 * 学生
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时28分
 */
public class UserEntity extends DataEntity {

    /**
     * 名字
     */
    public String name;
    /**
     * 班级id
     */
    public String classId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
