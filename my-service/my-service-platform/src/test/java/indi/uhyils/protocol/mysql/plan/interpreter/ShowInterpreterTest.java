package indi.uhyils.protocol.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLShowTablesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowColumnsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowDatabasesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowVariantsStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.Asserts;
import java.util.Objects;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年11月24日 09时35分
 */
public class ShowInterpreterTest {

    @Test
    public void parseVariants() {
        String sql = "show variables like 'lower_case_%'";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowVariantsStatement);
    }

    @Test
    public void parseDatabases() {
        String sql = "show databases";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowDatabasesStatement);
    }

    @Test
    public void parseShowColumns() {
        String sql = "show columns from sys_user";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowColumnsStatement);
        MySqlShowColumnsStatement mySqlShowColumnsStatement = (MySqlShowColumnsStatement) sqlStatement;
        String tableName = mySqlShowColumnsStatement.getTable().toString();
        Asserts.assertTrue(Objects.equals(tableName, "sys_user"));
    }

    @Test
    public void parseTables() {
        String sql = "show tables";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof SQLShowTablesStatement);
        sql = "show tables from mysql";
        sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof SQLShowTablesStatement);
        SQLShowTablesStatement showTables = (SQLShowTablesStatement) sqlStatement;
        SQLName database = showTables.getDatabase();
        String s = database.toString();
        Asserts.assertTrue(Objects.equals("mysql", s));
    }
}
