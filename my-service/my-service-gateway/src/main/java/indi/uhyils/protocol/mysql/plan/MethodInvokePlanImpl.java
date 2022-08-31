package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class MethodInvokePlanImpl extends MethodInvokePlan {

    public MethodInvokePlanImpl(List<MysqlPlan> lastPlans, Map<String, String> headers, String methodName, List<SQLExpr> arguments) {
        super(lastPlans, headers, methodName, arguments);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
