package indi.uhyils.trace;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.model.TraceIdDoDO;

/**
 * 处理traceDeal
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月02日 08时33分
 */
public interface TraceDealInterface<T extends TraceIdDoDO> {

    /**
     * 处理
     *
     * @param traceMsg
     */
    void doDeal(String traceMsg);

    /**
     * 初始化数据库处理
     *
     * @param dao
     */
    void init(DefaultDao<T> dao);
}
