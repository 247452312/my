package indi.uhyils.plan;

import indi.uhyils.enums.MysqlPlanTypeEnum;
import indi.uhyils.plan.result.MysqlPlanResult;
import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 08时40分
 */
public interface MysqlPlan extends PlanResultObserver {

    /**
     * 执行执行计划
     *
     * @return
     */
    MysqlPlanResult invoke();

    /**
     * 补全执行计划参数
     *
     * @param planArgs 计划参数<执行计划id,执行计划结果>
     */
    void complete(Map<Long, List<Map<String, Object>>> planArgs);

    /**
     * 获取此执行计划的类型
     *
     * @return
     */
    MysqlPlanTypeEnum type();

    long getId();

}
