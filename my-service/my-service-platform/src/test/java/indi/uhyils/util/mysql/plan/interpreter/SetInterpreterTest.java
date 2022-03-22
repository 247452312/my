package indi.uhyils.util.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLAssignItem;
import com.alibaba.druid.sql.ast.statement.SQLSetStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.Asserts;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月24日 09时09分
 */
public class SetInterpreterTest {

    @Test
    public void parse() {
        String sql = "SET NAMES utf8mb4";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        List<SQLAssignItem> items = ((SQLSetStatement) sqlStatement).getItems();
        Asserts.assertTrue(items.size() == 1);
        SQLAssignItem sqlAssignItem = items.get(0);
        String target = sqlAssignItem.getTarget().toString();
        String value = sqlAssignItem.getValue().toString();
        Asserts.assertTrue(Objects.equals(target, "NAMES"));
        Asserts.assertTrue(Objects.equals(value, "utf8mb4"));

    }
}
