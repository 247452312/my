package indi.uhyils.pojo.entity;

import indi.uhyils.util.LogUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月08日 07时38分
 */
public class SelectSqlTest {

    private String sqlStr;

    @Before
    public void setUp() throws Exception {
        sqlStr = "SELECT\n"
              + "\tid,\n"
              + "\t( SELECT DISTINCT a FROM sys_a ) AS s \n"
              + "FROM\n"
              + "\tb \n"
              + "WHERE\n"
              + "\tid = 1 UNION\n"
              + "SELECT\n"
              + "\tid,\n"
              + "\ts \n"
              + "FROM\n"
              + "\tc \n"
              + "WHERE\n"
              + "\tid = 2 \n"
              + "\tAND NAME IN ( SELECT NAME FROM sys_name WHERE k = 1 AND ( ss = 1 OR bb = 1 ) ) UNION\n"
              + "SELECT\n"
              + "\t* \n"
              + "FROM\n"
              + "\tsys_aab aab\n"
              + "\tLEFT JOIN sys_bbc bbc ON aab.id = bbc.id where aab.delete_flag=0 UNION\n"
              + "SELECT\n"
              + "\t* \n"
              + "FROM\n"
              + "\t( SELECT * FROM asdasd ) a UNION\n"
              + "SELECT\n"
              + "\t* \n"
              + "FROM\n"
              + "\t( SELECT * FROM asdasd WHERE delete_flag = 0 ) a";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void fillDeleteFlag() {
        Sql sql = new Sql(sqlStr);
        SelectSql transformation = (SelectSql) sql.transformation();
        transformation.fillDeleteFlag();
        LogUtil.info(transformation.sql());
    }
}
