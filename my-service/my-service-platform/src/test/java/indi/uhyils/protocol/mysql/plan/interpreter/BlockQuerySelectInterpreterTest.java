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
import org.junit.jupiter.api.Test;

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

    /**
     * join查询
     */
    @Test
    public void doParse2() {
        String sqlStr = "select a.*,b.* from sys_user a left join sys_role b on a.role_id = b.id where a.id = 1";
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

    /**
     * where测试
     */
    @Test
    public void doParseWhere() {
        String sqlStr = "select * from sys_user a where a.id = 1 and a.role_id in (select id from sys_role b where b.name = 'abc')";
        SQLSelectStatement sql = (SQLSelectStatement) new MySqlStatementParser(sqlStr).parseStatement();

        SQLSelect select = sql.getSelect();
        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) select.getQuery();
        SQLExpr where = query.getWhere();

        String sqlStr2 = "select * from sys_user a where a.id = 1 and a.role_id in (1,2,3)";
        SQLSelectStatement sql2 = (SQLSelectStatement) new MySqlStatementParser(sqlStr2).parseStatement();

    }

    /**
     * selectList测试
     */
    @Test
    public void doParseList() {
        String sqlStr = "SELECT a.SCHEMA_NAME, DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME FROM information_schema.SCHEMATA a";
        SQLSelectStatement sql = (SQLSelectStatement) new MySqlStatementParser(sqlStr).parseStatement();

        MySqlSelectQueryBlock query = (MySqlSelectQueryBlock) sql.getSelect().getQuery();
        List<SQLSelectItem> selectList = query.getSelectList();

    }


}
