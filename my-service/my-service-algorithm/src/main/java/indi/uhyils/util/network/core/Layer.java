package indi.uhyils.util.network.core;

/**
 * 层级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 09时48分
 */
public interface Layer extends Cloneable {

    /**
     * 层级必须要有network(观察者模式)
     *
     * @param network 神经网络
     */
    void setNetwork(Network network);


    /**
     * 正向计算
     *
     * @param obj 上一层给的输入
     * @return 输出, 也是下一层的输出
     */
    Double[][][][] calculation(Double[][][] obj);

    /**
     * 反向计算(注:要包含学习过程)
     *
     * @param inData 输入误差
     * @return 上一层的误差
     */
    Object reverse(Double inData);

    /**
     * 初始化权重(随机初始化)
     *
     * @param minWeight 最小化权重
     * @param maxWeight 最大化权重
     */
    void initWeight(Double minWeight, Double maxWeight);

    /**
     * 以指定值初始化权重
     *
     * @param doubles 权重
     */
    void initWeight(Double[] doubles);

    /**
     * 获取此层输入size
     *
     * @return 此层输入size
     */
    Integer getInSize();


    /**
     * 获取此层输出size 即神经元size
     *
     * @return
     */
    Integer getOutSize();

    /**
     * 设置神经元个数
     *
     * @param size
     */
    void setNeuronSize(Integer size);
}
