package indi.uhyils.genetic.core;

/**
 * 个体(神经网络)
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时41分
 */
public interface Individual {

    /**
     * 获取此神经网络的所有层
     *
     * @return
     */
    Layer[] getLayers();

    /**
     * 初始化个体
     *
     * @param factors 初始因子
     * @param index   这个个体初始化时在种群中的下标
     * @return
     */
    boolean init(Integer[] factors, Integer index);

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
     * @return
     */
    boolean variation();

    /**
     * 诞生下一代
     *
     * @param individual 另一个个体
     * @return
     */
    boolean birth(Individual individual);


    /**
     * 前向计算
     *
     * @param food
     * @return
     */
    double[] forward(Food food);

}
