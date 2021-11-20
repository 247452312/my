package indi.uhyils.protocol.mysql.plan;

import java.util.List;

/**
 * mysql 查询条件
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年11月19日 15时00分
 */
public interface MysqlWhere extends MysqlPlanObserver {

    /**
     * 设置入参
     *
     * @param params
     */
    void setParams(List<Object> params);


}
