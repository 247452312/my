package indi.uhyils.plan.pojo.plan;

import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import java.util.List;

/**
 * 左连接执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时55分
 */
public abstract class LeftJoinSqlPlan extends AbstractMysqlSqlPlan {

    /**
     * 左边结果
     */
    private List<Long> leftResult;

    /**
     * 右边结果
     */
    private List<Long> rightResult;


    protected LeftJoinSqlPlan(List<MysqlPlan> lastPlans, String sql, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlans, sql, null);
        this.leftResult = leftPlanId;
        this.rightResult = rightPlanId;
    }
}
