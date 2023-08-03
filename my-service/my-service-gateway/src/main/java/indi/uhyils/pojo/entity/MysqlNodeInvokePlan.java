package indi.uhyils.pojo.entity;

import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.pojo.plan.BlockQuerySelectSqlPlan;

/**
 * node执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月03日 10时35分
 */
public class MysqlNodeInvokePlan extends BlockQuerySelectSqlPlan {

    private AbstractDataNode node;

    protected MysqlNodeInvokePlan(AbstractDataNode node, BlockQuerySelectSqlPlan selectSqlPlan, String sql) {
        super(selectSqlPlan.getId(), sql, selectSqlPlan.toHeaders(), selectSqlPlan.toParams());
        this.node = node;
    }

    @Override
    public NodeInvokeResult invoke() {
        NodeInvokeResult result = node.getResult(headers, params);
        result.setSourcePlan(this);
        return result;
    }
}
