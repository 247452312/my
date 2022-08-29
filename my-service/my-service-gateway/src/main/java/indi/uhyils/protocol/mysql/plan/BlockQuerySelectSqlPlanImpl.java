package indi.uhyils.protocol.mysql.plan;

import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;
import java.util.Map;

/**
 * 简单sql执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时31分
 */
public class BlockQuerySelectSqlPlanImpl extends BlockQuerySelectSqlPlan {

    public BlockQuerySelectSqlPlanImpl(List<MysqlPlan> mysqlPlan, SqlTableSourceBinaryTree froms, Map<String, Object> params) {
        super(mysqlPlan, froms, params);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
