package indi.uhyils.genetic.core;

/**
 * 基因对
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时43分
 */
public interface GenePair {

    /**
     * 获取第一个
     *
     * @return
     */
    Gene getFirst();


    /**
     * 获取第二个
     *
     * @return
     */
    Gene getSecond();

    /**
     * 变异
     *
     * @return
     */
    boolean variation();

}
