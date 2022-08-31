package indi.uhyils.protocol.mysql.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.mysql.pojo.DTO.NodeInvokeResult;
import indi.uhyils.plan.MysqlPlan;
import indi.uhyils.plan.pojo.plan.ResultMappingPlan;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 16时33分
 */
public class ResultMappingPlanImpl extends ResultMappingPlan {

    public ResultMappingPlanImpl(List<MysqlPlan> mysqlPlans, Map<String, String> headers, List<SQLSelectItem> selectList) {
        super(mysqlPlans, headers, selectList);
    }

    @Override
    public NodeInvokeResult invoke() {
        return null;
    }
}
