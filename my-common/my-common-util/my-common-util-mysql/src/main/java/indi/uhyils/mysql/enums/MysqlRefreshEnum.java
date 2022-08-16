package indi.uhyils.mysql.enums;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月05日 22时18分
 */
public enum MysqlRefreshEnum {
    /**
     *
     */
    REFRESH_GRANT((byte) 0x01, "刷新授权"),
    REFRESH_LOG((byte) 0x02, "刷新日志"),
    REFRESH_TABLES((byte) 0x04, "刷新表"),
    REFRESH_HOSTS((byte) 0x08, "刷新hosts"),
    REFRESH_STATUS((byte) 0x10, "刷新服务器状态"),
    REFRESH_THREADS((byte) 0x20, "刷新线程"),
    REFRESH_SLAVE((byte) 0x40, "刷新从库"),
    REFRESH_MASTER((byte) 0x80, "刷新主库");

    private final byte code;

    private final String msg;

    MysqlRefreshEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static MysqlRefreshEnum parse(byte code) {
        for (MysqlRefreshEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
