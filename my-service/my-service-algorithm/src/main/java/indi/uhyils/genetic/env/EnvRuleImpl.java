package indi.uhyils.genetic.env;

import indi.uhyils.genetic.core.EnvRule;

import java.util.Arrays;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 09时26分
 */
public class EnvRuleImpl implements EnvRule {
    @Override
    public double[] getSurvivalCoefficient(double[] out, double[] should) {
        if (out == null || should == null) {
            throw new RuntimeException("结果和目标值存在为空的情况");
        }
        if (out.length != should.length) {
            throw new RuntimeException("结果和目标值长度不同");
        }
        double[] copyOfOut = Arrays.copyOf(out, out.length);
        for (int i = 0; i < copyOfOut.length; i++) {
            copyOfOut[i] = (copyOfOut[i] - should[i]) / should[i];
        }
        return copyOfOut;
    }
}
