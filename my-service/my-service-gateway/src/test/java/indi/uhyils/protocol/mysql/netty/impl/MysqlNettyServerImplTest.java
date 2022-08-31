package indi.uhyils.protocol.mysql.netty.impl;


import indi.uhyils.BaseTest;
import indi.uhyils.mysql.util.MysqlUtil;
import indi.uhyils.plan.MysqlPlan;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月17日 14时25分
 */
public class MysqlNettyServerImplTest extends BaseTest {

    @Test
    void analysisSql() {
        String sql = "select a.*,b.*,b.id from sys_user a left join sys_role b on a.role_id = b.id where a.id = 12 and b.name = (select max(name) from sys_role where delete_flag = 0) and (b.name,b.id) in (select name,id from sys_user order by id desc limit 2)  ";
        final List<MysqlPlan> mysqlPlans = MysqlUtil.analysisSqlToPlan(sql, null);
        int i = 1;
    }

    @Test
    void analysisSql2() {
        String sql = "select schema_name,default_character_set_name,default_collation_name from information_schema.schemata";
        final List<MysqlPlan> mysqlPlans = MysqlUtil.analysisSqlToPlan(sql, null);
        int i = 1;
    }


}
