package indi.uhyils.protocol.mysql;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.PlanFactory;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.LeftJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.plan.pojo.plan.RightJoinSqlPlan;
import indi.uhyils.protocol.mysql.plan.BlockQuerySelectSqlPlanImpl;
import indi.uhyils.protocol.mysql.plan.InnerJoinSqlPlanImpl;
import indi.uhyils.protocol.mysql.plan.LeftJoinSqlPlanImpl;
import indi.uhyils.protocol.mysql.plan.MethodInvokePlanImpl;
import indi.uhyils.protocol.mysql.plan.ResultMappingPlanImpl;
import indi.uhyils.protocol.mysql.plan.RightJoinSqlPlanImpl;
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
    public InnerJoinSqlPlan buildInnerJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new InnerJoinSqlPlanImpl(headers, leftPlanId, rightPlanId);
    }

    @Override
    public LeftJoinSqlPlan buildLeftJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new LeftJoinSqlPlanImpl(headers, leftPlanId, rightPlanId);
    }

    @Override
    public MethodInvokePlan buildMethodInvokePlan(Map<String, String> headers, String methodName, List<SQLExpr> arguments) {
        return new MethodInvokePlanImpl(headers, methodName, arguments);
    }

    @Override
    public ResultMappingPlan buildResultMappingPlan(Map<String, String> headers, List<SQLSelectItem> selectList) {
        return new ResultMappingPlanImpl(headers, selectList);
    }

    @Override
    public RightJoinSqlPlan buildRightJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new RightJoinSqlPlanImpl(headers, leftPlanId, rightPlanId);
    }
}
