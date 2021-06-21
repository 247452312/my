package indi.uhyils.genetic.core;

import java.util.List;

/**
 * 神经网络层
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时41分
 */
public interface Layer {

    /**
     * 获取这一层的神经元
     *
     * @return
     */
    Neuron[] getNeurons();

    /**
     * 学习
     * 转向因子: 个数等同于神经元的数量,值等同于 下一层传递过来的因子的平均值
     *
     * @param turningFactor 转向因子
     * @return 上一层的转向因子
     */
    List<Double> learn(List<Double> turningFactor);
}
