package indi.uhyils.enum_;

/**
 * 日志类型
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月08日 14时40分
 */
public enum LogTypeEnum {
    /**
     * 正常日志
     */
    NOMAL(0),
    /**
     * 错误日志
     */
    ERROR(1);
    private Integer type;

    LogTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
