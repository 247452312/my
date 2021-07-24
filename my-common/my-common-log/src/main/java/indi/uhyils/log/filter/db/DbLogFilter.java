package indi.uhyils.log.filter.db;

import com.alibaba.druid.filter.FilterChain;
import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import indi.uhyils.log.LogTypeEnum;
import indi.uhyils.log.MyTraceIdContext;

import java.sql.SQLException;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月24日 17时05分
 */
public class DbLogFilter extends FilterEventAdapter {

    @Override
    public boolean statement_execute(FilterChain chain, StatementProxy statement, String sql) throws SQLException {
        long startTime = System.currentTimeMillis();
        boolean result = super.statement_execute(chain, statement, sql);
        long timeConsuming = System.currentTimeMillis() - startTime;
        MyTraceIdContext
                .printLogInfo(LogTypeEnum.DB, startTime, timeConsuming);
        return result;
    }
}
