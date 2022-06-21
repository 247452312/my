package indi.uhyils.druid.filter.limit;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.PreparedStatementProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import indi.uhyils.util.LogUtil;
import java.sql.SQLException;
import java.util.Properties;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月08日 13时17分
 */
@Component
public class LimitFilter extends FilterEventAdapter {

    private DruidFilterConfiguration druidFilterConfiguration;

    @Override
    public void configFromProperties(Properties properties) {
        if (this.druidFilterConfiguration == null) {
            this.druidFilterConfiguration = new DruidFilterConfiguration();
        }

        String rowsLimitInterrupt = properties.getProperty("druid.rows.limit.interrupt");
        String rowsLimitSize = properties.getProperty("druid.rows.limit.size");
        String logThresholdSize = properties.getProperty("druid.rows.limit.log.threshold.size");
        if (rowsLimitInterrupt != null) {
            this.druidFilterConfiguration.setRowsLimitInterrupt(Boolean.valueOf(rowsLimitInterrupt));
        }

        if (rowsLimitSize != null) {
            this.druidFilterConfiguration.setRowsLimitSize(Integer.parseInt(rowsLimitSize));
        }

        if (logThresholdSize != null) {
            this.druidFilterConfiguration.setLogThresholdSize(Integer.parseInt(logThresholdSize));
        }

        LogUtil.info(String.format("[rowsLimitFilter] interrupt:%s,size:%s,", this.druidFilterConfiguration.getRowsLimitInterrupt(), this.druidFilterConfiguration.getRowsLimitSize()));
    }

    private void commonBefore(StatementProxy statement) {
        try {
            // 此处只支持mysql的JDBC 其他的会报错
            statement.getRawObject().setFetchSize(Integer.MIN_VALUE);
        } catch (SQLException var3) {
            LogUtil.error(String.format("setFetchSize  occur error %s", statement));
        }

    }

    @Override
    protected void statementExecuteQueryBefore(StatementProxy statement, String sql) {
        this.commonBefore(statement);
    }

    @Override
    protected void statementExecuteBefore(StatementProxy statement, String sql) {
        this.commonBefore(statement);
    }

    @Override
    public ResultSetProxy statement_executeQuery(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        return new ResultSetWrapperImpl(statement, super.statement_executeQuery(chain, statement, sql), this.druidFilterConfiguration);
    }

    @Override
    public ResultSetProxy preparedStatement_executeQuery(FilterChain chain, PreparedStatementProxy statement) throws SQLException {
        return new ResultSetWrapperImpl(statement, super.preparedStatement_executeQuery(chain, statement), this.druidFilterConfiguration);
    }

    @Override
    public ResultSetProxy statement_getResultSet(FilterChain chain, StatementProxy statement) throws SQLException {
        return new ResultSetWrapperImpl(statement, super.statement_getResultSet(chain, statement), this.druidFilterConfiguration);
    }
}
