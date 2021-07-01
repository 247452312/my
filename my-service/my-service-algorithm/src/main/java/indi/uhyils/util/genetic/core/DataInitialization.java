package indi.uhyils.util.genetic.core;

/**
 * 种群操作工具
 *
 * @param <T> 遗传算法中的个体
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 08时14分
 */
public interface DataInitialization<T extends Data> {

    /**
     * 获取设置好的欧氏距离
     *
     * @return
     */
    Double getDistance();

    /**
     * 设置离得近的个体可以看做一个个体的最小值
     *
     * @param distance
     */
    void setDistance(Double distance);

    /**
     * 随机初始化种群
     *
     * @return 随机出来的一个东西
     */
    T random();


    /**
     * 杂交
     * {@ps 允许自交将优秀的基因遗传下去}
     *
     * @param one      一个data
     * @param otherOne 另一个data
     * @return 下一代
     */
    T hybridization(T one, T otherOne);

    /**
     * 在判断两个个体之间是不是一个点时计算的欧氏距离
     *
     * @return 两个点之间的距离
     */
    Double getDistance(T one, T tow);

    /**
     * 变异
     *
     * @param data                要变异的种群
     * @param mutationProbability 变异概率
     */
    void variation(T data, Double mutationProbability);
}
