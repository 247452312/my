package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.Asserts;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月20日 14时15分
 */
public class UnionSelectInterpreterTest {

    @Test
    public void doCanParse() {
        String sql = "select a.name from a union all select b.name from b";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof SQLSelectStatement && ((SQLSelectStatement) sqlStatement).getSelect().getQuery() instanceof SQLUnionQuery, "类型错误");

    }
}