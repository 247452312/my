package indi.uhyils.plan.pojo.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import indi.uhyils.plan.MysqlPlan;
import java.util.List;

/**
 * 结果映射执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时57分
 */
public abstract class ResultMappingPlan extends AbstractMysqlSqlPlan {

    private final List<SQLSelectItem> selectList;

    protected ResultMappingPlan(List<MysqlPlan> mysqlPlans, List<SQLSelectItem> selectList) {
        super(mysqlPlans, null, null);
        this.selectList = selectList;
    }
}
