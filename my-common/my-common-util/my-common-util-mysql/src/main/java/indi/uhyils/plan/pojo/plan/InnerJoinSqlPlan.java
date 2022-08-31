package indi.uhyils.plan.pojo.plan;

import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全连接执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时59分
 */
public abstract class InnerJoinSqlPlan extends AbstractMysqlSqlPlan {


    /**
     * 左边结果
     */
    private List<Long> leftResult;

    /**
     * 右边结果
     */
    private List<Long> rightResult;

    protected InnerJoinSqlPlan(List<MysqlPlan> lastPlan, String sql, Map<String, String> headers, List<Long> leftPlanId, List<Long> rightPlanId) {
        super(lastPlan, sql, headers, new HashMap<>());
        this.leftResult = leftPlanId;
        this.rightResult = rightPlanId;
    }


}