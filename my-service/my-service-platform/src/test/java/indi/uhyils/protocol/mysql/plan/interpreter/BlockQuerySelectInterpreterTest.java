package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Objects;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时19分
 */
public class BlockQuerySelectInterpreterTest {

    @Test
    public void doCanParse() {
        String sql = "select a.name from a";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof SQLSelectStatement && ((SQLSelectStatement) sqlStatement).getSelect().getQuery() instanceof MySqlSelectQueryBlock, "类型错误");

    }

    @Test
    public void doParse() {
        String sqlStr = "select a.name from sys_user a";
        SQLSelectStatement sql = (SQLSelectStatement) new MySqlStatementParser(sqlStr).parseStatement();

        SQLSelect select = sql.getSelect();
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        List<SQLSelectItem> selectList = query.getSelectList();
        SQLExpr where = query.getWhere();
        Asserts.assertTrue(where == null, "解析错误");
        SQLTableSource from = query.getFrom();
        String alias = from.getAlias();
        Asserts.assertTrue(Objects.equals(from.toString(), "sys_user"), "解析错误");
        Asserts.assertTrue(Objects.equals(alias, "a"), "解析错误");
    }

    /**
     * 子查询
     */
    @Test
    public void doParse1() {
        String sqlStr = "select a.name from (select * from sys_user) a";
        SQLSelectStatement sql = (SQLSelectStatement) new MySqlStatementParser(sqlStr).parseStatement();

        SQLSelect select = sql.getSelect();
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        SQLTableSource from = query.getFrom();
        String alias = from.getAlias();
        Asserts.assertTrue(from instanceof SQLSubqueryTableSource, "解析错误");
        Asserts.assertTrue(Objects.equals(alias, "a"), "解析错误");
        SQLSubqueryTableSource tableSourceFrom = (SQLSubqueryTableSource) from;

        select = tableSourceFrom.getSelect();
        query = (MySqlSelectQueryBlock) select.getQuery();
        from = query.getFrom();
        alias = from.getAlias();
        Asserts.assertTrue(from instanceof SQLExprTableSource, "解析错误");
        Asserts.assertTrue(alias == null, "解析错误");
    }


}