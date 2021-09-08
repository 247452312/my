package indi.uhyils.pojo.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 08时40分
 */
public class UpdateSqlTest {

    private String sqlStr;

    @Before
    public void setUp() throws Exception {
        sqlStr = "update sys_user a set name = ?, kik=? where id = (select * from biz_lis_sample b where b.sample_no = ?)";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addStringSet() {
        Sql sql = new Sql(sqlStr);
        UpdateSql updateSql = (UpdateSql) sql.transformation();
        updateSql.addStringSet("kiu", "2");
        String sql1 = updateSql.sql();
        System.out.println(sql1);
        System.out.println();

        updateSql.addDateSet("update_time", System.currentTimeMillis());
        sql1 = updateSql.sql();
        System.out.println(sql1);
        System.out.println();

        updateSql.fillDeleteFlag();
        sql1 = updateSql.sql();
        System.out.println(sql1);
        System.out.println();

    }
}
