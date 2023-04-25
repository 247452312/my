package indi.uhyils.plan.pojo.plan;

import indi.uhyils.plan.AbstractMysqlSqlPlan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * union语句执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时56分
 */
public abstract class UnionSqlPlan extends AbstractMysqlSqlPlan {

    /**
     * 左边结果
     */
    protected List<Long> unionPlanIds;


    protected UnionSqlPlan(String sql, Map<String, String> headers, List<Long> unionPlanIds) {
        super(sql, headers, new HashMap<>());
        this.unionPlanIds = unionPlanIds;
    }
}
