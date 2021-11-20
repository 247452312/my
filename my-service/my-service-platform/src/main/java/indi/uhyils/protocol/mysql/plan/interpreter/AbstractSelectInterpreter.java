package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import indi.uhyils.protocol.mysql.plan.MysqlPlan;
import java.util.List;

/**
 * 查询解释器
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 11时18分
 */
public abstract class AbstractSelectInterpreter extends AbstractInterpreter {

    @Override
    public boolean canParse(SQLStatement sql) {
        if (!(sql instanceof SQLSelectStatement)) {
            return false;
        }
        return doCanParse((SQLSelectStatement) sql);
    }

    @Override
    public List<MysqlPlan> parse(SQLStatement sql) {
        return doParse((SQLSelectStatement) sql);
    }

    /**
     * 是否是指定语句
     *
     * @param sql
     *
     * @return
     */
    protected abstract boolean doCanParse(SQLSelectStatement sql);

    /**
     * 解析
     *
     * @param sql
     *
     * @return
     */
    protected abstract List<MysqlPlan> doParse(SQLSelectStatement sql);

}
