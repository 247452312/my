package indi.uhyils.protocol.mysql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.PlanFactory;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.LeftJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.plan.pojo.plan.RightJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.impl.InnerJoinSqlPlanImpl;
import indi.uhyils.plan.pojo.plan.impl.LeftJoinSqlPlanImpl;
import indi.uhyils.plan.pojo.plan.impl.MethodInvokePlanImpl;
import indi.uhyils.plan.pojo.plan.impl.ResultMappingPlanImpl;
import indi.uhyils.plan.pojo.plan.impl.RightJoinSqlPlanImpl;
import indi.uhyils.plan.pojo.plan.impl.UnionSqlPlanImpl;
import indi.uhyils.protocol.mysql.plan.BlockQuerySelectSqlPlanImpl;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时13分
 */
@Component
public class PlanFactoryImpl implements PlanFactory {

    @Override
    public BlockQuerySelectSqlPlan buildBlockQuerySelectSqlPlan(SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        return new BlockQuerySelectSqlPlanImpl(froms, headers, params);
    }

    @Override
    public InnerJoinSqlPlan buildInnerJoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        return new InnerJoinSqlPlanImpl(headers, tree, leftPlanId, rightPlanId);
    }

    @Override
    public LeftJoinSqlPlan buildLeftJoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        return new LeftJoinSqlPlanImpl(headers, tree, leftPlanId, rightPlanId);
    }

    @Override
    public MethodInvokePlan buildMethodInvokePlan(Map<String, String> headers, Integer resultIndex, String methodName, List<SQLExpr> arguments, SQLMethodInvokeExpr invokeExpr) {
        SQLObject parent = invokeExpr.getParent();
        String asName;
        if (parent instanceof SQLSelectItem) {
            asName = ((SQLSelectItem) parent).getAlias();
        } else {
            asName = invokeExpr.getOwner() != null ? invokeExpr.getOwner().toString() : null;
        }
        return new MethodInvokePlanImpl(headers, resultIndex, methodName, arguments, asName);
    }

    @Override
    public ResultMappingPlan buildResultMappingPlan(Map<String, String> headers, MysqlPlan lastMainPlan, List<SQLSelectItem> selectList) {
        return new ResultMappingPlanImpl(headers, lastMainPlan, selectList);
    }

    @Override
    public RightJoinSqlPlan buildRightJoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        return new RightJoinSqlPlanImpl(headers, tree, leftPlanId, rightPlanId);
    }

    @Override
    public MysqlPlan buildUnionSelectSqlPlan(Map<String, String> headers, List<Long> planIds) {
        return new UnionSqlPlanImpl(headers, planIds);
    }
}
