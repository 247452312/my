package indi.uhyils.plan;

import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;

/**
 * 空白执行计划,什么都不做
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月01日 08时58分
 */
public class EmptyMysqlPlan extends AbstractMysqlSqlPlan {

    public EmptyMysqlPlan(List<MysqlPlan> lastPlan) {
        super(lastPlan, null, null);
    }

    @Override
    public MysqlPlanResult invoke() {
        return null;
    }
}
