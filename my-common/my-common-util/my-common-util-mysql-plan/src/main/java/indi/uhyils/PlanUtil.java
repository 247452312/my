package indi.uhyils;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.parser.SqlParser;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 执行计划工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时38分
 */
public final class PlanUtil {

    private PlanUtil() {
        throw new RuntimeException("工具类不能初始化");
    }


    /**
     * 解析sql语句
     *
     * @param sql
     *
     * @return
     */
    public static MysqlPlan analysisSql(String sql) {
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        List<SqlParser> beans = SpringUtil.getBeans(SqlParser.class);
        for (SqlParser bean : beans) {
            if (bean.canParse(sqlStatement)) {
                return bean.parse(sqlStatement).get(0);
            }
        }
        Asserts.assertTrue(false, "解析执行计划失败:{}", sql);
        return null;
    }

    /**
     * 执行执行计划
     */
    public static void execute(List<MysqlPlan> plan, Map<String, Object> params) {
        // 初始化参数
        Map<Long, List<Map<String, Object>>> planMap = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        value.add(params);
        planMap.put(-1L, value);

        // 补全并执行
        for (MysqlPlan mysqlPlan : plan) {
            mysqlPlan.complete(planMap);
            MysqlPlanResult invoke = mysqlPlan.invoke();
            List<Map<String, Object>> result = invoke.result();
            planMap.put(mysqlPlan.getId(), result);
        }
    }

}
