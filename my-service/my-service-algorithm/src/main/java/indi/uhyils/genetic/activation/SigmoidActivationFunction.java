package indi.uhyils.genetic.activation;

import indi.uhyils.genetic.core.ActivationFunction;


/**
 * Sigmoid激活函数
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月30日 08时30分
 */
public class SigmoidActivationFunction implements ActivationFunction {
    @Override
    public double forward(double result) {
        double pow = Math.pow(Math.E, -result);
        return 1.0 / (1.0 + pow);
    }

    @Override
    public double reverse(double result) {
        assert result != 1;
        return Math.log(result / (1 - result));
    }
}
