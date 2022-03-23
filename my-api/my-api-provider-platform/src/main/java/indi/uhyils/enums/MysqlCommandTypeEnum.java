package indi.uhyils.enums;

import indi.uhyils.util.Asserts;
import java.util.Objects;

/**
 * mysql请求类型代码
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月03日 18时29分
 */
public enum MysqlCommandTypeEnum {
    /**
     * 名称就是注释
     */
    COM_QUERY(0x03, "SQL查询请求", true),
    COM_FIELD_LIST(0x04, "获取数据表字段信息", true),
    COM_TABLE_DUMP(0x13, "获取数据表结构信息", true),
    COM_STMT_EXECUTE(0x17, "执行预处理语句", true),
    COM_STMT_PREPARE(0x16, "预处理SQL语句", true),
    COM_STMT_SEND_LONG_DATA(0x18, "发送BLOB类型的数据", true),
    COM_PROCESS_INFO(0x0A, "获取当前连接的列表", true),
    COM_PROCESS_KILL(0x0C, "中断某个连接", true),
    COM_STATISTICS(0x09, "获取服务器统计信息", true),

    COM_SLEEP(0x00, "内部线程状态", false),
    COM_QUIT(0x01, "关闭连接", false),
    COM_INIT_DB(0x02, "切换数据库", false),
    COM_CREATE_DB(0x05, "创建数据库", false),
    COM_DROP_DB(0x06, "删除数据库", false),
    COM_REFRESH(0x07, "清除缓存", false),
    COM_SHUTDOWN(0x08, "停止服务器", false),
    COM_CONNECT(0x0B, "内部线程状态", false),
    COM_DEBUG(0x0D, "保存服务器调试信息", false),
    COM_PING(0x0E, "测试连通性", false),
    COM_TIME(0x0F, "内部线程状态", false),
    COM_DELAYED_INSERT(0x10, "内部线程状态", false),
    COM_CHANGE_USER(0x11, "重新登陆（不断连接）", false),
    COM_BINLOG_DUMP(0x12, "获取二进制日志信息", false),
    COM_CONNECT_OUT(0x14, "内部线程状态", false),
    COM_REGISTER_SLAVE(0x15, "从服务器向主服务器进行注册", false),
    COM_STMT_CLOSE(0x19, "销毁预处理语句", false),
    COM_STMT_RESET(0x1A, "清除预处理语句参数缓存", false),
    COM_SET_OPTION(0x1B, "设置语句选项", false),
    COM_STMT_FETCH(0x1C, "获取预处理语句的执行结果", false);

    /**
     * 代码
     */
    private final long code;

    /**
     * 名称
     */
    private final String name;

    /**
     * 是否需要服务调用才能获取源码
     */
    private final Boolean needService;

    MysqlCommandTypeEnum(long code, String name, Boolean needService) {
        this.code = code;
        this.name = name;
        this.needService = needService;
    }

    /**
     * 执行
     *
     * @param code
     *
     * @return
     */
    public static MysqlCommandTypeEnum parse(long code) {
        for (MysqlCommandTypeEnum value : values()) {
            if (Objects.equals(value.getCode(), code)) {
                return value;
            }
        }
        Asserts.assertTrue(false, "");
        return null;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getNeedService() {
        return needService;
    }
}
