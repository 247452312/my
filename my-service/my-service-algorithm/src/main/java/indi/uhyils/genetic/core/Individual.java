package indi.uhyils.genetic.core;

/**
 * 个体
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
     * @param factors
     * @return
     */
    boolean init(Integer[] factors);

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

}
