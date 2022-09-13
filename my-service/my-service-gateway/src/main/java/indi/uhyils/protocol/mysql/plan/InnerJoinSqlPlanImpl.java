package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class InnerJoinSqlPlanImpl extends InnerJoinSqlPlan {

    public InnerJoinSqlPlanImpl(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(null, headers, leftPlanId, rightPlanId);
    }

    @Override
    public NodeInvokeResult invoke() {
        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult();
        nodeInvokeResult.setFieldInfos(new ArrayList<>());
        nodeInvokeResult.setResult(new ArrayList<>());
        return nodeInvokeResult;
    }
}
