package indi.uhyils.plan.pojo.plan;

import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import java.util.Map;

/**
 * 全连接执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时59分
 */
public abstract class InnerJoinSqlPlan extends JoinSqlPlan {


    protected InnerJoinSqlPlan(Map<String, String> headers, SqlTableSourceBinaryTree tree, Long leftPlanId, Long rightPlanId) {
        super(headers, tree, leftPlanId, rightPlanId);
    }
}
