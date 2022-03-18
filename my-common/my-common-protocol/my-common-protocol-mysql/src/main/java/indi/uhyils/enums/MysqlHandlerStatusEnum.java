package indi.uhyils.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 14时56分
 */
public enum MysqlHandlerStatusEnum {
    /**
     * 客户端请求连接 到客户端发回认证信息之前的阶段
     */
    FIRST_SIGHT(0, "初见"),
    /**
     * 客户端认证后到断开连接之前
     */
    PASSED(1, "认证通过"),
    /**
     * 客户端断开连接之后
     */
    OVER(2, "结束");

    /**
     * 代码
     */
    private final Integer code;

    /**
     * 名称
     */
    private final String name;

    MysqlHandlerStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
