package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class InnerJoinSqlPlanImpl extends InnerJoinSqlPlan {

    public InnerJoinSqlPlanImpl(List<MysqlPlan> lastPlan, String sql, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlan, sql, leftPlanId, rightPlanId);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
