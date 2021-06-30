package indi.uhyils.genetic.activation;

import indi.uhyils.genetic.core.ActivationFunction;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月30日 09时06分
 */
public class TanhActivationFunction implements ActivationFunction {
    @Override
    public double forward(double result) {
        double v = Math.pow(Math.E, 2 * result) + 1;
        return 1 - 2.0 / v;
    }

    @Override
    public double reverse(double result) {
        assert result != 1;
        double v = (1 + result) / (1 - result);
        return 0.5 * Math.log(v);
    }
}
