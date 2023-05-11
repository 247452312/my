package indi.uhyils.plan;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.pojo.plan.InnerJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.LeftJoinSqlPlan;
import indi.uhyils.plan.pojo.plan.MethodInvokePlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.plan.pojo.plan.RightJoinSqlPlan;
import java.util.List;
import java.util.Map;

/**
 * 执行计划工厂(集成spring,使用方应该实现此工厂,并使用bean的形式结合到spring中)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时54分
 */
public interface PlanFactory {

    /**
     * 创建一个简单sql执行计划
     *
     * @return
     */
    BlockQuerySelectSqlPlan buildBlockQuerySelectSqlPlan(SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params);


    /**
     * 创建一个全连接执行计划
     *
     * @return
     */
    InnerJoinSqlPlan buildInnerJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId);


    /**
     * 创建一个左连接执行计划
     *
     * @return
     */
    LeftJoinSqlPlan buildLeftJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId);


    /**
     * 创建一个执行方法的执行计划
     *
     * @param headers
     * @param resultIndex 结果坐标
     * @param methodName  方法名称
     * @param arguments   参数
     * @param asName      别名
     *
     * @return
     */
    MethodInvokePlan buildMethodInvokePlan(Map<String, String> headers, Integer resultIndex, String methodName, List<SQLExpr> arguments, SQLExpr asName);


    /**
     * 创建一个结果映射执行计划
     *
     * @return
     */
    ResultMappingPlan buildResultMappingPlan(Map<String, String> headers, MysqlPlan lastMainPlan, List<SQLSelectItem> selectList);


    /**
     * 创建一个右连接执行计划
     *
     * @return
     */
    RightJoinSqlPlan buildRightJoinSqlPlan(Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId);


    /**
     * 创建一个union sql执行计划
     *
     * @param planIds
     * @param headers
     *
     * @return
     */
    MysqlPlan buildUnionSelectSqlPlan(Map<String, String> headers, List<Long> planIds);
}
