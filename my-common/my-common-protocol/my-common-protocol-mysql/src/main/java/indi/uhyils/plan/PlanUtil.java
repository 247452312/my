package indi.uhyils.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.plan.interpreter.Interpreter;
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

    /**
     * 解析入参
     *
     * @param expr
     *
     * @return
     */
    public static JSONObject parasSQLBinaryOpExpr(SQLBinaryOpExpr expr) {
        String paramName = expr.getLeft().toString();
        SQLExpr right = expr.getRight();
        SQLBinaryOperator operator = expr.getOperator();
        switch (operator) {
            case Is:
            case Equality:
                break;
            // 大于
            case GreaterThan:
                paramName = paramName + "&>";
                break;
            // 小于
            case LessThan:
                paramName = paramName + "&<";
                break;
            // 大于等于
            case GreaterThanOrEqual:
                paramName = paramName + "&>=";
                break;
            // 大于等于
            case LessThanOrEqual:
                paramName = paramName + "&<=";
                break;
            default:
                Asserts.assertTrue(false, "不支持操作符:{}", operator.toString());

        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", paramName);
        jsonObject.put("value", right);
        return jsonObject;
    }

}
