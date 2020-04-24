package indi.uhyils.model;



/**
 * 班级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月20日 11时32分
 */
public class ClassEntity extends DataEntity {

    /**
     * 班级名称
     */
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
