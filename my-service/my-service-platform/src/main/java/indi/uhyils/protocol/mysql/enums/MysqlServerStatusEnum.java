package indi.uhyils.protocol.mysql.enums;

/**
 * 服务器状态
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月04日 08时30分
 */
public enum MysqlServerStatusEnum {
    /**
     * 传输中
     */
    SERVER_STATUS_IN_TRANS(0x0001),
    /**
     * 自动提交
     */
    SERVER_STATUS_AUTOCOMMIT(0x0002),
    /**
     * 游标遍历中
     */
    SERVER_STATUS_CURSOR_EXISTS(0x0040),
    /**
     * 数据最后一行已发送,即发送完毕
     */
    SERVER_STATUS_LAST_ROW_SENT(0x0080),
    /**
     * 数据库删除成功
     */
    SERVER_STATUS_DB_DROPPED(0x0100),
    /**
     *
     */
    SERVER_STATUS_NO_BACKSLASH_ESCAPES(0x0200),
    /**
     * 元数据更改完成,即表结构变更
     */
    SERVER_STATUS_METADATA_CHANGED(0x0400);

    private final int code;

    MysqlServerStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
