package indi.uhyils.plan.pojo.plan.impl;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class InnerJoinSqlPlanImpl extends InnerJoinSqlPlan {

    public InnerJoinSqlPlanImpl(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        super(headers, tree, leftPlanId, rightPlanId);
    }

    @Override
    public NodeInvokeResult invoke() {
        NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(this);
        nodeInvokeResult.setFieldInfos(allFieldInfo());
        List<Map<String, Object>> result = new ArrayList<>();
        /*此处两个不同行列数的table 需要融合在一起 on中的条件是融合前需要遵守的,也是合并表的依据 where 是合并后进行筛选*/
        nodeInvokeResult.setResult(result);
        return nodeInvokeResult;
    }
}
