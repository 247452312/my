package indi.uhyils.genetic.core;

/**
 * 激活函数
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 08时32分
 */
public interface ActivationFunction {


    /**
     * 正向计算
     *
     * @param result 正向计算中与权重相乘相加计算出来的值
     * @return
     */
    double forward(double result);


    /**
     * 反向计算
     *
     * @param result 反向计算中此神经节点需要修正的值
     * @return
     */
    double reverse(double result);

}
