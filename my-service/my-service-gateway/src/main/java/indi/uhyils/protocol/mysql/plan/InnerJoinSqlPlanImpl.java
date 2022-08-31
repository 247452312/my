package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class InnerJoinSqlPlanImpl extends InnerJoinSqlPlan {

    public InnerJoinSqlPlanImpl(List<MysqlPlan> lastPlan, String sql, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlan, sql, headers, leftPlanId, rightPlanId);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
