package indi.uhyils.protocol.mysql.netty.impl;


import indi.uhyils.BaseTest;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanUtil;
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
        final List<MysqlPlan> mysqlPlans = PlanUtil.analysisSql(sql);
        int i = 1;
    }
}
