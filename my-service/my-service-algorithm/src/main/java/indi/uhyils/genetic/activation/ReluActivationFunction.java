package indi.uhyils.genetic.activation;

import indi.uhyils.genetic.core.ActivationFunction;


/**
 * Sigmoid激活函数
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月30日 08时30分
 */
public class ReluActivationFunction implements ActivationFunction {
    @Override
    public double forward(double result) {
        return Math.max(0, result);
    }

    @Override
    public double reverse(double result) {
        return Math.max(0, result);
    }
}
