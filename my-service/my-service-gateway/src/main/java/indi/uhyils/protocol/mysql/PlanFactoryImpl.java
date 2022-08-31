package indi.uhyils.protocol.mysql;

import com.alibaba.druid.sql.ast.SQLExpr;
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
    public BlockQuerySelectSqlPlan buildBlockQuerySelectSqlPlan(List<MysqlPlan> mysqlPlan, SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        return new BlockQuerySelectSqlPlanImpl(mysqlPlan, froms, headers, params);
    }

    @Override
    public InnerJoinSqlPlan buildInnerJoinSqlPlan(List<MysqlPlan> lastPlan,  Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new InnerJoinSqlPlanImpl(lastPlan, headers, leftPlanId, rightPlanId);
    }

    @Override
    public LeftJoinSqlPlan buildLeftJoinSqlPlan(List<MysqlPlan> lastPlans, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new LeftJoinSqlPlanImpl(lastPlans, headers, leftPlanId, rightPlanId);
    }

    @Override
    public MethodInvokePlan buildMethodInvokePlan(List<MysqlPlan> lastPlans, Map<String, String> headers, String methodName, List<SQLExpr> arguments) {
        return new MethodInvokePlanImpl(lastPlans, headers, methodName, arguments);
    }

    @Override
    public ResultMappingPlan buildResultMappingPlan(List<MysqlPlan> mysqlPlans, Map<String, String> headers, List<SQLSelectItem> selectList) {
        return new ResultMappingPlanImpl(mysqlPlans, headers, selectList);
    }

    @Override
    public RightJoinSqlPlan buildRightJoinSqlPlan(List<MysqlPlan> lastPlans, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        return new RightJoinSqlPlanImpl(lastPlans, headers, leftPlanId, rightPlanId);
    }
}
