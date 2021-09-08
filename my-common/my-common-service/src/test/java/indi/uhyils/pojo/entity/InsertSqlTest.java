package indi.uhyils.pojo.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 13时15分
 */
public class InsertSqlTest {

    private String sqlStr;

    @Before
    public void setUp() throws Exception {
        sqlStr = "insert into sys_user(name,nick_name) values(?,?)";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sql() {
        Sql sql = new Sql(sqlStr);
        InsertSql insertSql = (InsertSql) sql.transformation();
        String sql1 = insertSql.sql();
        System.out.println(sql1);
        System.out.println();

        insertSql.addDateItem("create_date", System.currentTimeMillis());
        insertSql.addDateItem("create_date", System.currentTimeMillis());
        insertSql.addDateItem("create_date", System.currentTimeMillis());
        insertSql.addLongItem("update_date", System.currentTimeMillis());
        System.out.println(insertSql.sql());
        System.out.println();

        sqlStr = "insert into sys_user(name,nick_name) select name,nick_name from sys_user_b where id = 1";
        Sql sql2 = new Sql(sqlStr);
        InsertSql insertSql3 = (InsertSql) sql2.transformation();
        insertSql3.fillDeleteFlag();
        String sql3 = insertSql3.sql();
        System.out.println(sql3);
    }

}
