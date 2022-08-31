package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.LeftJoinSqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class LeftJoinSqlPlanImpl extends LeftJoinSqlPlan {

    public LeftJoinSqlPlanImpl(List<MysqlPlan> lastPlans, String sql, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlans, sql,headers, leftPlanId, rightPlanId);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
