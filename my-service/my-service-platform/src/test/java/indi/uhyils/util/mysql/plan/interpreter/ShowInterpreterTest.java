package indi.uhyils.util.mysql.plan.interpreter;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLName;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLShowTablesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowColumnsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowCreateDatabaseStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowCreateTableStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowDatabasesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowGrantsStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowIndexesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowPrivilegesStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowProcessListStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowTableStatusStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowVariantsStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.util.Asserts;
import java.util.Objects;
import org.junit.jupiter.api.Test;

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
    public void parseShowProcess() {
        String sql = "show processlist";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowProcessListStatement);
        MySqlShowProcessListStatement mySqlShowProcessListStatement = (MySqlShowProcessListStatement) sqlStatement;
        boolean full = mySqlShowProcessListStatement.isFull();
        Asserts.assertTrue(full == false);
        sql = "show full processlist";
        sqlStatement = new MySqlStatementParser(sql).parseStatement();
        mySqlShowProcessListStatement = (MySqlShowProcessListStatement) sqlStatement;
        full = mySqlShowProcessListStatement.isFull();
        Asserts.assertTrue(full == true);
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
        String mysql = database.toString();
        Asserts.assertTrue(Objects.equals("mysql", mysql));
    }

    @Test
    public void parseShowGrants() {
        String sql = "show grants for xyz";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowGrantsStatement);
    }

    @Test
    public void parseShowStatus() {
        String sql = "show status ";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowStatusStatement);
        sql = "show status like '%aaa%'";
        sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowStatusStatement);
        MySqlShowStatusStatement mySqlShowStatusStatement = (MySqlShowStatusStatement) sqlStatement;
        SQLExpr like = mySqlShowStatusStatement.getLike();
        String s = like.toString();
        Asserts.assertTrue(Objects.equals(s, "'%aaa%'"));
    }

    @Test
    public void parseShowIndex() {
        String sql = "show index from sys_user ";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowIndexesStatement);
        MySqlShowIndexesStatement mySqlShowIndexesStatement = (MySqlShowIndexesStatement) sqlStatement;
        SQLName database = mySqlShowIndexesStatement.getDatabase();
        SQLName table = mySqlShowIndexesStatement.getTable();
        Asserts.assertTrue(database == null);
        Asserts.assertTrue(Objects.equals(table.toString(), "sys_user"));
    }

    @Test
    public void parseShowPrivileges() {
        String sql = "show privileges";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowPrivilegesStatement);
    }

    @Test
    public void parseShowTableStatus() {
        String sql = "show table status;";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowTableStatusStatement);
        sql = "show table status like '%aaa%';";
        sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowTableStatusStatement);
        MySqlShowTableStatusStatement mySqlShowTableStatusStatement = (MySqlShowTableStatusStatement) sqlStatement;
        String s = mySqlShowTableStatusStatement.getLike().toString();
        Asserts.assertTrue(Objects.equals(s, "'%aaa%'"));

        sql = "show table status from sys_user;";
        sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowTableStatusStatement);
        mySqlShowTableStatusStatement = (MySqlShowTableStatusStatement) sqlStatement;
        s = mySqlShowTableStatusStatement.getDatabase().toString();
        Asserts.assertTrue(Objects.equals(s, "sys_user"));

    }

    @Test
    public void parseShowCreateDatabase() {
        String sql = "show create database abc;";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowCreateDatabaseStatement);
        MySqlShowCreateDatabaseStatement mySqlShowCreateDatabaseStatement = (MySqlShowCreateDatabaseStatement) sqlStatement;
        String s = mySqlShowCreateDatabaseStatement.getDatabase().toString();
        Asserts.assertTrue(Objects.equals(s, "abc"));
    }
    @Test
    public void parseShowCreateTable() {
        String sql = "show create table sys_user;";
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        Asserts.assertTrue(sqlStatement instanceof MySqlShowCreateTableStatement);
        MySqlShowCreateTableStatement mySqlShowCreateTableStatement = (MySqlShowCreateTableStatement) sqlStatement;
        String s = mySqlShowCreateTableStatement.getName().toString();
        Asserts.assertTrue(Objects.equals(s, "sys_user"));
    }

}
