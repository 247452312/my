package indi.uhyils.plan.pojo.plan;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import indi.uhyils.plan.AbstractMysqlSqlPlan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结果映射执行计划
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月26日 15时57分
 */
public abstract class ResultMappingPlan extends AbstractMysqlSqlPlan {

    protected final List<SQLSelectItem> selectList;

    protected ResultMappingPlan(Map<String, String> headers, List<SQLSelectItem> selectList) {
        super(null, headers, new HashMap<>());
        this.selectList = selectList;
    }
}
