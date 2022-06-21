package indi.uhyils.druid.filter.limit;

import com.alibaba.druid.proxy.jdbc.ResultSetProxy;
import com.alibaba.druid.proxy.jdbc.ResultSetProxyImpl;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import indi.uhyils.util.LogUtil;
import java.sql.SQLException;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月08日 13时33分
 */
public class ResultSetWrapperImpl extends ResultSetProxyImpl {

    public volatile boolean isPrintLog;

    private int rowsLimitSize;

    private int logThresholdSize;

    private Boolean rowsLimitInterrupt;

    private Boolean rowsLimitEnable;

    private int curCount;

    public ResultSetWrapperImpl(StatementProxy statement, ResultSetProxy resultSetProxy, DruidFilterConfiguration druidFilterConfiguration) {
        super(statement, resultSetProxy.getResultSetRaw(), resultSetProxy.getId(), resultSetProxy.getSql());
        this.isPrintLog = Boolean.FALSE;
        this.rowsLimitEnable = true;
        this.curCount = 0;
        if (druidFilterConfiguration == null) {
            druidFilterConfiguration = new DruidFilterConfiguration();
        }

        this.rowsLimitSize = druidFilterConfiguration.getRowsLimitSize();
        this.rowsLimitInterrupt = druidFilterConfiguration.getRowsLimitInterrupt();
        this.logThresholdSize = druidFilterConfiguration.getLogThresholdSize();
    }

    @Override
    public boolean next() throws SQLException {
        ++this.curCount;
        Boolean isLimit;
        if (RowsLimitThreadContext.getRowsLimitEnableInCurrentThread() != null) {
            isLimit = RowsLimitThreadContext.getRowsLimitEnableInCurrentThread();
        } else {
            isLimit = this.rowsLimitEnable;
        }

        int limit;
        if (RowsLimitThreadContext.getRowsLimitSizeInCurrentThread() != null) {
            limit = RowsLimitThreadContext.getRowsLimitSizeInCurrentThread();
        } else {
            limit = this.rowsLimitSize;
        }

        if (this.curCount > this.logThresholdSize && !this.isPrintLog) {
            LogUtil.info("[rowsLimit]|" + this.getSql() + "|单次查询记录数超过|" + this.logThresholdSize + "|条");
            this.isPrintLog = Boolean.TRUE;
        }

        if (isLimit && this.curCount > limit && this.rowsLimitInterrupt) {
            throw new SQLException("[rowsLimit]|" + this.getSql() + "|单次查询记录数超过|" + limit + "|条");
        } else {
            return super.next();
        }
    }

}
