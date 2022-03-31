package indi.uhyils.pool;

/**
 * 实例池
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 14时37分
 */
public interface ObjectPool<T> {

    /**
     * 获取一个实例池中的实例
     *
     * @return
     */
    T getOrCreateObject();

    /**
     * 实例池剩余实例数量
     *
     * @return
     */
    long remainderCount();

}
