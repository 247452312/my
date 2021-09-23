package indi.uhyils.util.network.core;

/**
 * 神经网络层
 * T 代表输入数据格式
 * E 代表输出数据格式
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 09时37分
 */
public interface Network {


    /**
     * 训练
     *
     * @param times    数据集训练多少遍
     * @param bachSize batch大小
     */
    TrainResult train(Integer times, Integer bachSize);

    /**
     * 获取每次训练的数量
     *
     * @return 每次训练的数量
     */
    Integer getBatchSize();

    /**
     * 预测
     *
     * @param t 数据
     *
     * @return 结果
     */
    Resultable predict(Datable t);

    /**
     * 设置层级
     *
     * @param layer      神经网络层
     * @param startFloor 起始层级
     * @param size       要几层这个神经网络层
     */
    void setLayer(Layer layer, Integer startFloor, Integer size);

    /**
     * 获取学习率
     *
     * @return
     */
    Double getLearningRate();

    /**
     * 加载模型
     *
     * @param modelPath 模型路径
     */
    void loadModel(String modelPath);

    /**
     * 保存模型
     *
     * @param modelPath 模型路径
     */
    void saveModel(String modelPath);
}
