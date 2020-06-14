package indi.uhyils.enum_;

/**
 * 服务器类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月12日 13时11分
 */
public enum ServerTypeEnum {
    /**
     * windows 10
     */
    WINDOWS_10(1, "windows_10"),

    /**
     * centos 7
     */
    CENTOS_7(2, "centos_7");


    private Integer type;

    private String name;

    ServerTypeEnum(Integer type, String name) {
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
