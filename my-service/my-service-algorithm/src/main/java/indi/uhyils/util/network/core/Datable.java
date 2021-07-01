package indi.uhyils.util.network.core;

/**
 * 可作为数据集的东西
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 09时41分
 */
public interface Datable<T> {
    /**
     * 获取数据
     *
     * @return 数据
     */
    Object getData();

    /**
     * 获取此数据的size
     *
     * @return
     */
    int[] getSize();

}
