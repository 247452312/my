package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class InnerJoinSqlPlanImpl extends InnerJoinSqlPlan {

    public InnerJoinSqlPlanImpl(List<MysqlPlan> lastPlan, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlan, null, headers, leftPlanId, rightPlanId);
    }

    @Override
    public NodeInvokeResult invoke() {
        return null;
    }
}
