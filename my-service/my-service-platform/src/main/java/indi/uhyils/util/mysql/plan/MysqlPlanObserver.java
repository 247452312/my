package indi.uhyils.util.mysql.plan;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月19日 15时05分
 */
public interface MysqlPlanObserver {

    /**
     * 获取对应的多个mysql执行计划
     *
     * @return
     */
    List<MysqlPlan> getMysqlPlan();
}
