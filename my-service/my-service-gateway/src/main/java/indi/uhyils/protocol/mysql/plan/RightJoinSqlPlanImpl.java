package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.plan.RightJoinSqlPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时34分
 */
public class RightJoinSqlPlanImpl extends RightJoinSqlPlan {

    public RightJoinSqlPlanImpl(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
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
