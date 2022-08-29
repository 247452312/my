package indi.uhyils.plan.pojo.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import java.util.List;

/**
 * 执行方法的执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时57分
 */
public abstract class MethodInvokePlan extends AbstractMysqlSqlPlan {

    private final String methodName;

    private final List<SQLExpr> arguments;

    protected MethodInvokePlan(List<MysqlPlan> lastPlans, String methodName, List<SQLExpr> arguments) {
        super(lastPlans, null, null);
        this.methodName = methodName;
        this.arguments = arguments;
    }

}
