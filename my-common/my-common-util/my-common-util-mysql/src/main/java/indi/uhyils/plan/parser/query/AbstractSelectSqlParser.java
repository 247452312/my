package indi.uhyils.plan.parser.query;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.parser.SqlParser;
import indi.uhyils.plan.pojo.pool.SqlTableSourceBinaryTreePool;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import java.util.List;
import java.util.Map;
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
public abstract class AbstractSelectSqlParser implements SqlParser {

    @Autowired
    protected SqlTableSourceBinaryTreePool pool;

    /**
     * sql解析
     */
    private List<AbstractSelectSqlParser> selectInterpreters;

    /**
     * 重新解析一个sql
     *
     * @param fromSql
     * @param sqlExecuteFunction sql解析成一个执行计划之后需要做什么
     */
    protected <T> T reExecute(String fromSql, Map<String, String> headers, Function<List<MysqlPlan>, T> sqlExecuteFunction) {
        // 检查解析器是否初始化
        checkInterpreters();
        SQLSelectStatement fromSqlStatement = (SQLSelectStatement) new MySqlStatementParser(fromSql).parseStatement();
        for (AbstractSelectSqlParser selectInterpreter : selectInterpreters) {
            if (selectInterpreter.canParse(fromSqlStatement)) {
                List<MysqlPlan> parse = selectInterpreter.parse(fromSqlStatement, headers);
                return sqlExecuteFunction.apply(parse);
            }
        }
        return null;
    }

    /**
     * 检查解析器是否初始化
     */
    private void checkInterpreters() {
        if (selectInterpreters == null) {
            selectInterpreters = SpringUtil.getBeans(AbstractSelectSqlParser.class);
        }
    }

    @Override
    public boolean canParse(SQLStatement sql) {
        if (!(sql instanceof SQLSelectStatement)) {
            return false;
        }
        return doCanParse((SQLSelectStatement) sql);
    }

    @Override
    public List<MysqlPlan> parse(SQLStatement sql, Map<String, String> headers) {
        return doParse((SQLSelectStatement) sql, headers);
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
    protected abstract List<MysqlPlan> doParse(SQLSelectStatement sql, Map<String, String> headers);

    /**
     * 重新解析一个sql
     *
     * @param fromSql
     * @param reExecute
     */
    protected List<MysqlPlan> reExecute(String fromSql, Map<String, String> headers, Consumer<List<MysqlPlan>> reExecute) {
        //检查解析器是否初始化
        checkInterpreters();
        SQLSelectStatement fromSqlStatement = (SQLSelectStatement) new MySqlStatementParser(fromSql).parseStatement();
        for (AbstractSelectSqlParser selectInterpreter : selectInterpreters) {
            if (selectInterpreter.canParse(fromSqlStatement)) {
                List<MysqlPlan> parse = selectInterpreter.parse(fromSqlStatement, headers);
                reExecute.accept(parse);
                return parse;
            }
        }
        Asserts.throwException("错误,未找到对应的解析类,语句为:{}", fromSql);
        return null;
    }

}
