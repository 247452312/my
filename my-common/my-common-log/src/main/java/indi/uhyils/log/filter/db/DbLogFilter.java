package indi.uhyils.log.filter.db;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.JdbcParameter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.SQLUtils.FormatOption;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.log.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;
import indi.uhyils.util.LogUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月24日 17时05分
 */
@Component
public class DbLogFilter extends FilterEventAdapter {

    private FormatOption statementSqlFormatOption = new FormatOption(false, true);

    @Override
    public boolean preparedStatement_execute(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        String preparedSql = statement.getSql();
        String sql = changeSqlPlaceholder(preparedSql, statement.getParameters());
        if (LogUtil.isDebugEnabled(this)) {
            LogUtil.debug(this, sql);
        }
        String sqlType = getSqlType(sql);
        return MyTraceIdContext.printLogInfo(LogTypeEnum.DB, () -> {
            try {
                return super.preparedStatement_execute(chain, statement);
            } catch (SQLException e) {
                LogUtil.error(this, e);
            }
            return false;
        }, new String[]{sqlType}, preparedSql);
    }

    /**
     * 获取sql类型
     *
     * @param sql
     *
     * @return
     */
    private String getSqlType(String sql) {
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseStatement();
        String sqlType;
        if (sqlStatement instanceof SQLSelectStatement) {
            sqlType = "select";
        } else if (sqlStatement instanceof SQLInsertStatement) {
            sqlType = "insert";
        } else if (sqlStatement instanceof SQLUpdateStatement) {
            sqlType = "update";
        } else if (sqlStatement instanceof SQLDeleteStatement) {
            sqlType = "delete";
        } else {
            sqlType = sqlStatement.getClass().getName();
        }
        return sqlType;
    }

    /**
     * 替换占位符
     *
     * @param sql
     * @param parameterMap
     *
     * @return
     */
    private String changeSqlPlaceholder(String sql, Map<Integer, JdbcParameter> parameterMap) {
        List<Object> parameters = new ArrayList<>(parameterMap.size());
        for (int i = 0; i < parameterMap.size(); ++i) {
            JdbcParameter jdbcParam = parameterMap.get(i);
            parameters.add(jdbcParam != null
                               ? jdbcParam.getValue()
                               : null);
        }
        return SQLUtils.format(sql, "mysql", parameters, this.statementSqlFormatOption);
    }

}
