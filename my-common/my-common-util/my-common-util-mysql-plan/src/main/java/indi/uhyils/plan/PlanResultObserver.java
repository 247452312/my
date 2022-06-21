package indi.uhyils.plan;

import java.util.List;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年04月01日 08时27分
 */
public interface PlanResultObserver {

    /**
     * 获取之前执行计划的结果
     *
     * @return
     */
    Map<Long, List<Map<String, Object>>> getPlanResult();

}
