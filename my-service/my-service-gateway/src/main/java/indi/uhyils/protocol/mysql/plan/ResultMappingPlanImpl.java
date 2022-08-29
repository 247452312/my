package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class ResultMappingPlanImpl extends ResultMappingPlan {

    public ResultMappingPlanImpl(List<MysqlPlan> mysqlPlans, List<SQLSelectItem> selectList) {
        super(mysqlPlans, selectList);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
