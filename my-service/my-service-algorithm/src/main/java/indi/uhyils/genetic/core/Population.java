package indi.uhyils.genetic.core;

/**
 * 种群
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时40分
 */
public interface Population {

    /**
     * 初始化种群
     *
     * @param factors 初始化种群要素
     * @return 是否初始化成功
     */
    boolean init(Integer[] factors);


    /**
     * 淘汰
     *
     * @return
     */
    boolean eliminate();


    /**
     * 学习
     *
     * @param foods 食物集
     * @return
     */
    boolean learn(Food[] foods);


    /**
     * 变异
     *
     * @param probability 变异概率
     * @return
     */
    boolean variation(double probability);


    /**
     * 诞生下一代
     *
     * @param ratio 种群数量增加比例
     * @return
     */
    boolean birth(double ratio);


    /**
     * 迭代
     *
     * @param foods       食物集
     * @param probability 变异概率
     * @param ratio       种群数量增加比例
     * @return
     */
    boolean iteration(Food[] foods, double probability, double ratio);


}
