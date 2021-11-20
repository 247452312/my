package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import indi.uhyils.protocol.mysql.plan.interpreter.Interpreter;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.SpringUtil;
import java.util.List;

/**
 * 执行计划util
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月19日 14时57分
 */
public final class PlanUtil {

    private PlanUtil() {
        throw new RuntimeException("不能初始化");
    }

    public static List<MysqlPlan> parseSqlToPlan(String sql) {
        SQLStatement sqlStatement = new MySqlStatementParser(sql).parseStatement();
        List<Interpreter> beans = SpringUtil.getBeans(Interpreter.class);
        for (Interpreter bean : beans) {
            if (bean.canParse(sqlStatement)) {
                return bean.parse(sqlStatement);
            }
        }
        Asserts.assertTrue(false, "解析执行计划失败:{}", sql);
        return null;
    }


}
