package indi.uhyils.util.mysql.plan.interpreter.query;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.mysql.plan.MysqlPlan;
import indi.uhyils.util.mysql.plan.interpreter.AbstractInterpreter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询解释器
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 11时18分
 */
public abstract class AbstractSelectInterpreter extends AbstractInterpreter {

    /**
     * sql解析
     */
    @Autowired
    private List<AbstractSelectInterpreter> selectInterpreters;


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
     * 重新解析一个sql
     *
     * @param fromSql
     * @param sqlExecuteFunction sql解析成一个执行计划之后需要做什么
     */
    protected <T> T reExecute(String fromSql, Function<List<MysqlPlan>, T> sqlExecuteFunction) {
        SQLSelectStatement fromSqlStatement = (SQLSelectStatement) new MySqlStatementParser(fromSql).parseStatement();
        for (AbstractSelectInterpreter selectInterpreter : selectInterpreters) {
            if (selectInterpreter.canParse(fromSqlStatement)) {
                List<MysqlPlan> parse = selectInterpreter.parse(fromSqlStatement);
                return sqlExecuteFunction.apply(parse);
            }
        }
        return null;
    }

    /**
     * 重新解析一个sql
     *
     * @param fromSql
     * @param reExecute
     */
    protected void reExecute(String fromSql, Consumer<List<MysqlPlan>> reExecute) {
        SQLSelectStatement fromSqlStatement = (SQLSelectStatement) new MySqlStatementParser(fromSql).parseStatement();
        for (AbstractSelectInterpreter selectInterpreter : selectInterpreters) {
            if (selectInterpreter.canParse(fromSqlStatement)) {
                List<MysqlPlan> parse = selectInterpreter.parse(fromSqlStatement);
                reExecute.accept(parse);
                break;
            }
        }
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
