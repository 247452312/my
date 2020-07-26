package indi.uhyils.util.network.core;

/**
 * 激活函数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 10时12分
 */
public interface ActivationFunction {
    /**
     * 正向计算
     *
     * @param inData 输入数据
     * @return 结果
     */
    Double forward(Double inData);


    /**
     * 反向传播
     *
     * @param inData 输出数据
     * @return 结果
     */
    Double Reverse(Double inData);
}
