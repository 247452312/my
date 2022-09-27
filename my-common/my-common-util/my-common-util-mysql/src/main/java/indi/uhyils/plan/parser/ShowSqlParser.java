package indi.uhyils.plan.parser;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLShowTablesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowDatabasesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowEventsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowFunctionCodeStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowFunctionStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowGrantsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowIndexesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowKeysStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowMasterLogsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowMasterStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowOpenTablesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowPluginsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowPrivilegesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProcedureCodeStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProcedureStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProcessListStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProfileStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProfilesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowRelayLogEventsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowSlaveHostsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowSlaveStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowTableStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowTriggersStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowVariantsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowWarningsStatement;
import indi.uhyils.mysql.content.MysqlContent;
import indi.uhyils.mysql.handler.MysqlTcpInfo;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.StringUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时11分
 */
@Component
public class ShowSqlParser implements SqlParser {


    @Override
    public boolean canParse(SQLStatement sql) {
        if (sql instanceof MySqlShowStatement) {
            return true;
        }
        if (sql instanceof SQLShowTablesStatement) {
            return true;
        }
        return false;
    }


    @Override
    public List<MysqlPlan> parse(SQLStatement sql, Map<String, String> headers) {
        // todo show命令解析
        LogUtil.info("show:{}", sql.toLowerCaseString());
        List<MysqlPlan> result = null;
        try {
            final Method doParse = this.getClass().getDeclaredMethod("doParse", sql.getClass(), Map.class);
            doParse.setAccessible(true);
            final Object invoke = doParse.invoke(this, sql, headers);
            Asserts.assertTrue(invoke != null, "未知的show语句:{}", sql.toLowerCaseString());
            result = (List<MysqlPlan>) invoke;
            doParse.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LogUtil.error(this, e);
        }
        return result;
    }

    private List<MysqlPlan> doParse(MySqlShowEventsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowFunctionCodeStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowFunctionStatusStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowGrantsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowIndexesStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowKeysStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowMasterLogsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowMasterStatusStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowOpenTablesStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowPluginsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowPrivilegesStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowProcedureCodeStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowProcedureStatusStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowProcessListStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowProfileStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowProfilesStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowRelayLogEventsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowSlaveHostsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowSlaveStatusStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowStatusStatement sql, Map<String, String> headers) {
        final SQLExpr like = sql.getLike();
        StringBuilder transSql = new StringBuilder("select VARIABLE_NAME as VariableName,VARIABLE_VALUE as `Value` from performance_schema.GLOBAL_VARIABLES where 1=1");
        if (like != null) {
            transSql.append(" and VARIABLE_NAME like '");
            transSql.append(like);
            transSql.append("'");
        }
        return MysqlUtil.analysisSqlToPlan(transSql.toString(), headers);
    }

    private List<MysqlPlan> doParse(MySqlShowTableStatusStatement sql, Map<String, String> headers) {
        final MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        Asserts.assertTrue(mysqlTcpInfo != null, "No database selected");
        final String database = mysqlTcpInfo.getDatabase();
        Asserts.assertTrue(StringUtil.isNotEmpty(database), "No database selected");

        final SQLExpr like = sql.getLike();
        StringBuilder transSql = new StringBuilder().append("SELECT\n")
                                                    .append("\tTABLE_NAME AS `Name`,\n")
                                                    .append("\t`ENGINE` AS `Engine`,\n")
                                                    .append("\t`VERSION` AS `Version`,\n")
                                                    .append("\tROW_FORMAT AS Row_format,\n")
                                                    .append("\tTABLE_ROWS AS `Rows`,\n")
                                                    .append("\tAVG_ROW_LENGTH AS `Avg_row_length`,\n")
                                                    .append("\tDATA_LENGTH AS `Data_length`,\n")
                                                    .append("\tMAX_DATA_LENGTH AS `Max_data_length`,\n")
                                                    .append("\tINDEX_LENGTH AS `Index_length`,\n")
                                                    .append("\tDATA_FREE as `Data_free`,\n")
                                                    .append("\tAUTO_INCREMENT as `Auto_increment`,\n")
                                                    .append("\tCREATE_TIME as `Create_time`,\n")
                                                    .append("\tUPDATE_TIME as `Update_time`,\n")
                                                    .append("\tCHECK_TIME as `Check_time`,\n")
                                                    .append("\tTABLE_COLLATION as `Collation`,\n")
                                                    .append("\tCHECKSUB as `Checksub`,\n")
                                                    .append("\tCREATE_OPTIONS as `Create_options`,\n")
                                                    .append("\tTABLE_COMMENT as `Comment`\n")
                                                    .append("FROM\n")
                                                    .append("\tinformation_schema.`TABLES` where TABLE_SCHEMA='")
                                                    .append(database)
                                                    .append("' ");
        if (like != null) {
            transSql.append(" and TABLE_NAME like '");
            transSql.append(like);
            transSql.append("'");
        }
        return MysqlUtil.analysisSqlToPlan(transSql.toString(), headers);
    }

    private List<MysqlPlan> doParse(MySqlShowTriggersStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(MySqlShowVariantsStatement sql, Map<String, String> headers) {
        final SQLExpr like = sql.getLike();
        StringBuilder transSql = new StringBuilder("select VARIABLE_NAME as VariableName,VARIABLE_VALUE as `Value` from performance_schema.GLOBAL_VARIABLES where 1=1");
        if (like != null) {
            transSql.append(" and VARIABLE_NAME like '");
            transSql.append(like);
            transSql.append("'");
        }
        return MysqlUtil.analysisSqlToPlan(transSql.toString(), headers);
    }

    private List<MysqlPlan> doParse(MySqlShowWarningsStatement sql, Map<String, String> headers) {
        return null;
    }

    private List<MysqlPlan> doParse(SQLShowTablesStatement sql, Map<String, String> headers) {
        final MysqlTcpInfo mysqlTcpInfo = MysqlContent.MYSQL_TCP_INFO.get();
        Asserts.assertTrue(mysqlTcpInfo != null, "No database selected");
        final String database = mysqlTcpInfo.getDatabase();
        Asserts.assertTrue(StringUtil.isNotEmpty(database), "No database selected");

        StringBuilder transSql = new StringBuilder("select TABLE_NAME as 'Tables_in_'" + database + " from information_schema.`TABLES` where TABLE_SCHEMA = '");
        transSql.append(database);
        transSql.append("'");

        return MysqlUtil.analysisSqlToPlan(transSql.toString(), headers);
    }

    private List<MysqlPlan> doParse(MySqlShowDatabasesStatement sql, Map<String, String> headers) {
        final SQLExpr like = sql.getLike();
        final SQLExpr where = sql.getWhere();
        StringBuilder transSql = new StringBuilder("select SCHEMA_NAME as `Database` from information_schema.SCHEMATA where 1=1");
        if (where != null) {
            transSql.append(" and ");
            transSql.append(where);
        }
        if (like != null) {
            transSql.append(" and ");
            transSql.append(" SCHEMA_NAME like '");
            transSql.append(like);
            transSql.append(" '");
        }
        return MysqlUtil.analysisSqlToPlan(transSql.toString(), headers);
    }

}
