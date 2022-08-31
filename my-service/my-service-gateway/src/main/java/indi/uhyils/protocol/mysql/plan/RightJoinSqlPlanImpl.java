package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.RightJoinSqlPlan;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时34分
 */
public class RightJoinSqlPlanImpl extends RightJoinSqlPlan {

    public RightJoinSqlPlanImpl(List<MysqlPlan> lastPlans, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlans, null, headers, leftPlanId, rightPlanId);
    }

    @Override
    public NodeInvokeResult invoke() {
        return null;
    }
}
