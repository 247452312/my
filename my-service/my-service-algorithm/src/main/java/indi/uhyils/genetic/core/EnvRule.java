package indi.uhyils.genetic.core;

/**
 * 自然选择
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月21日 08时49分
 */
public interface EnvRule {


    /**
     * 获取生存系数,也是最后一层的差值
     *
     * @param out    计算值
     * @param should 实际值
     * @return 生存系数 = (计算值 - 实际值)/实际值
     */
    double[] getSurvivalCoefficient(double[] out, double[] should);

}
