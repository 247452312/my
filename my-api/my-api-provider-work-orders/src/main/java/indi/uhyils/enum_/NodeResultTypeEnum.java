package indi.uhyils.enum_;

/**
 * 节点结果类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 11时32分
 */
public enum NodeResultTypeEnum {
    /**
     * 同name
     */
    SUCCESS("处理成功", 0),
    FAULT("处理失败", 1),
    TRANSFER("转交给别人", 2);


    private String name;
    private Integer code;

    NodeResultTypeEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
