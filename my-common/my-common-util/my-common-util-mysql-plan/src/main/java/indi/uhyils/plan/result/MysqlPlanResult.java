package indi.uhyils.plan.result;

import java.util.List;
import java.util.Map;

/**
 * 执行计划结果
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月29日 09时04分
 */
public interface MysqlPlanResult {

    /**
     * 获取执行计划结果
     *
     * @return
     */
    List<Map<String, Object>> result();

}
