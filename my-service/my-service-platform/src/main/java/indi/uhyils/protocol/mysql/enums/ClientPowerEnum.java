package indi.uhyils.protocol.mysql.enums;

/**
 * 客户端权限
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月08日 09时12分
 */
public enum ClientPowerEnum {
    /**
     * msg就是注释
     */
    CLIENT_LONG_PASSWORD(0x0001, "新的更安全的密码"),
    CLIENT_FOUND_ROWS(0x0002F, "找到而不是受影响的行"),
    CLIENT_LONG_FLAG(0x0004, "获取所有列标志"),
    CLIENT_CONNECT_WITH_DB(0x0008, "可以在connect上指定db"),
    CLIENT_NO_SCHEMA(0x0010, "不允许使用database.table.column"),
    CLIENT_COMPRESS(0x0020, "可以使用压缩协议"),
    CLIENT_ODBC(0x0040, "Odbc客户端"),
    CLIENT_LOCAL_FILES(0x0080, "可以使用本地加载数据"),
    CLIENT_IGNORE_SPACE(0x0100, "忽略“（”之前的空格"),
    CLIENT_PROTOCOL_41(0x0200, "新的4.1协议"),
    CLIENT_INTERACTIVE(0x0400, "这是一个交互式客户端"),
    CLIENT_SSL(0x0800, "握手后切换到SSL"),
    CLIENT_IGNORE_SIGPIPE(0x1000, "忽略信号管"),
    CLIENT_TRANSACTIONS(0x2000, "客户了解交易"),
    CLIENT_RESERVED(0x4000, "4.1协议的旧标志"),
    CLIENT_SECURE_CONNECTION(0x8000, "新的4.1身份验证"),
    CLIENT_MULTI_STATEMENTS(0x0001, " 启用/禁用多stmt支持"),
    CLIENT_MULTI_RESULTS(0x0002, " 启用/禁用多个结果");

    private final int code;

    private final String msg;

    ClientPowerEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
