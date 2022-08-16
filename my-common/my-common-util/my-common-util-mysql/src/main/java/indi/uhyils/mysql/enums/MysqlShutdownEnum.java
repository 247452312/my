package indi.uhyils.mysql.enums;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月06日 10时53分
 */
public enum MysqlShutdownEnum {
    /**
     *
     */
    SHUTDOWN_DEFAULT((byte) 0x00, "默认关机"),
    SHUTDOWN_WAIT_CONNECTIONS((byte) 0x01, "关机等待连接"),
    SHUTDOWN_WAIT_TRANSACTIONS((byte) 0x02, "关闭等待交易"),
    SHUTDOWN_WAIT_UPDATES((byte) 0x08, "关机等待更新"),
    SHUTDOWN_WAIT_ALL_BUFFERS((byte) 0x10, "关闭等待所有缓冲区"),
    SHUTDOWN_WAIT_CRITICAL_BUFFERS((byte) 0x11, "关断等待关键缓冲区"),
    KILL_QUERY((byte) 0xFE, "杀死查询"),
    KILL_CONNECTION((byte) 0xFF, "终止连接");

    private final byte code;

    private final String msg;


    MysqlShutdownEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
