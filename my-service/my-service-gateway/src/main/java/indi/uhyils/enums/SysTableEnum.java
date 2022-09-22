package indi.uhyils.enums;

import com.google.common.base.Function;
import indi.uhyils.annotation.NotNull;
import indi.uhyils.pojo.entity.sys.IColumns;
import indi.uhyils.pojo.entity.sys.IEngines;
import indi.uhyils.pojo.entity.sys.IParameters;
import indi.uhyils.pojo.entity.sys.IRoutines;
import indi.uhyils.pojo.entity.sys.ISchemata;
import indi.uhyils.pojo.entity.sys.ITables;
import indi.uhyils.pojo.entity.sys.IView;
import indi.uhyils.pojo.entity.sys.PGlobalVariables;
import indi.uhyils.pojo.entity.sys.SysTable;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.StringUtil;
import java.util.Map;

/**
 * 系统表枚举
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年09月01日 13时46分
 */
public enum SysTableEnum {
    /**
     * 库表元数据存储表
     */
    INFORMATION_SCHEMA_SCHEMATA("information_schema", "schemata", ISchemata::new),
    /**
     * 表元数据存储
     */
    INFORMATION_SCHEMA_TABLES("information_schema", "tables", ITables::new),
    /**
     * 列信息
     */
    INFORMATION_SCHEMA_COLUMNS("information_schema", "columns", IColumns::new),
    /**
     * 视图信息
     */
    INFORMATION_SCHEMA_PARAMETERS("information_schema", "parameters", IParameters::new),
    /**
     * 存储子程序（存储程序和函数）的信息
     */
    INFORMATION_SCHEMA_ROUTINES("information_schema", "routines", IRoutines::new),
    /**
     * 存储引擎相关的信息
     */
    INFORMATION_SCHEMA_ENGINES("information_schema", "engines", IEngines::new),
    /**
     * 视图相关的信息
     */
    INFORMATION_SCHEMA_VIEW("information_schema", "views", IView::new),
    /**
     * 系统参数
     */
    PERFORMANCE_SCHEMA_GLOBAL_VARIABLES("performance_schema", "global_variables", PGlobalVariables::new),
    ;

    /**
     * 库名称
     */
    private final String database;

    /**
     * 表名
     */
    private final String table;

    /**
     * 创建新SysTable的方法
     */
    private final Function<Map<String, Object>, SysTable> newSysTable;

    SysTableEnum(String database, String table, Function<Map<String, Object>, SysTable> newSysTable) {
        this.database = database;
        this.table = table;
        this.newSysTable = newSysTable;
    }

    /**
     * @param database
     * @param table
     *
     * @return
     */
    @NotNull
    public static SysTableEnum parse(String database, String table) {
        for (SysTableEnum value : values()) {
            if (StringUtil.equalsIgnoreCase(value.database, database)) {
                if (StringUtil.equalsIgnoreCase(value.table, table)) {
                    return value;
                }
            }
        }
        throw Asserts.makeException("未定义此系统表:{},{}", database, table);
    }

    public String getDatabase() {
        return database;
    }

    public String getTable() {
        return table;
    }

    /**
     * 获取系统表查询类
     *
     * @param params 入参
     *
     * @return
     */
    @NotNull
    public SysTable getSysTable(Map<String, Object> params) {
        return newSysTable.apply(params);
    }
}
