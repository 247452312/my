package indi.uhyils.enums;

/**
 * 中间件类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时20分
 */
public enum SoftwareTypeEnum {
    /**
     * redis
     */
    REDIS(1, "redis"),
    /**
     * zookeeper
     */
    ZOOKEEPER(2, "zookeeper"),

    /**
     * mysql
     */
    MYSQL(3, "mysql");

    private Integer type;

    private String name;


    SoftwareTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
