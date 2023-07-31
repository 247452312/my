package indi.uhyils.plan.pojo.plan.impl;

import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.LeftJoinSqlPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时32分
 */
public class LeftJoinSqlPlanImpl extends LeftJoinSqlPlan {

    public LeftJoinSqlPlanImpl(Map<String, String> headers, SqlTableSourceBinaryTree tree, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(headers, tree, leftPlanId, rightPlanId);
    }

    @Override
    public NodeInvokeResult invoke() {
        final NodeInvokeResult nodeInvokeResult = new NodeInvokeResult(this);
        nodeInvokeResult.setFieldInfos(allFieldInfo());
        /*此处两个不同行列数的table 需要融合在一起 on中的条件是融合前需要遵守的,也是合并表的依据 where 是合并后进行筛选*/
        // on里的条件
        List<SQLBinaryOpExpr> on = splitCondition();

        /*todo left join 中 以左边列表为准, 每条去遍历另一边的列表,判断on条件是否成立, 如果成立,则筛选出一条新数据*/
        nodeInvokeResult.setResult(new ArrayList<>());
        return nodeInvokeResult;
    }
}
