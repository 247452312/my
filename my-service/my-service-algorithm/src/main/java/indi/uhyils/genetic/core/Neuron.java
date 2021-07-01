package indi.uhyils.genetic.core;

/**
 * 神经元
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时42分
 */
public interface Neuron {

    /**
     * 获取这个神经元的激活函数
     *
     * @return
     */
    ActivationFunction getActivationFunction();


    /**
     * 获取这个神经元的基因链
     *
     * @return
     */
    DoubleHelix getDna();
}
