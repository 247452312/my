package indi.uhyils.pojo.model.base;

import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.pojo.request.base.DefaultRequest;
import java.io.Serializable;

/**
 * 什么都不干, 标记这是一个在数据库中有对应关系的类 其他数据库基类应该全部继承这个类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月23日 14时19分
 */
public interface BaseDbSaveable extends Serializable {

    /**
     * 插入前时使用的方法
     *
     * @param request 入参
     *
     * @throws IdGenerationException id生成错误
     * @throws InterruptedException  sleep线程报错
     */
    void preInsert(DefaultRequest request) throws IdGenerationException, InterruptedException;

    /**
     * 修改前时使用的方法
     *
     * @param request 请求
     */
    void preUpdate(DefaultRequest request);
}
