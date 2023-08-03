package indi.uhyils.plan.pojo.plan;

import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.pojo.SqlTableSourceBinaryTree;
import java.util.Map;

/**
 * 简单sql执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时00分
 */
public abstract class BlockQuerySelectSqlPlan extends AbstractMysqlSqlPlan {

    /**
     * table详情
     */
    protected SqlTableSourceBinaryTree froms;

    protected BlockQuerySelectSqlPlan(SqlTableSourceBinaryTree froms, Map<String, String> headers, Map<String, Object> params) {
        super(null, headers, params);
        this.froms = froms;
    }

    public BlockQuerySelectSqlPlan(Long id, String sql, Map<String, String> headers, Map<String, Object> params) {
        super(id, sql, headers, params);
    }

    public SqlTableSourceBinaryTree toTable() {
        return froms;
    }

}
