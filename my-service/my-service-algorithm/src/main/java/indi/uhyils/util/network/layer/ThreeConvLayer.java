package indi.uhyils.util.network.layer;

import indi.uhyils.util.network.core.ActivationFunction;
import indi.uhyils.util.network.core.Layer;
import indi.uhyils.util.network.core.Network;

/**
 * 三维卷积层(一维卷积层就是 -> 行数是1的二维卷积层 二维卷积层就是通道数为1的三维卷积层)
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月25日 13时24分
 */
public class ThreeConvLayer implements Layer {
    /**
     * 整个神经网络
     */
    private Network network;
    /**
     * 激活函数
     */
    private ActivationFunction activationFunction;

    /**
     * 卷积核们
     * [神经元位置][通道][卷积核行][卷积核列]
     */
    private Double[][][][] kernels;

    /**
     * 卷积核的高
     */
    private Integer kernelHeight;
    /**
     * 卷积核的宽
     */
    private Integer kernelWidth;

    /**
     * 卷积核的通道数
     */
    private Integer kernelChannel;

    public ThreeConvLayer(Integer kernelHeight, Integer kernelWidth, Integer kernelChannel, ActivationFunction activationFunction) {
        this.kernelHeight = kernelHeight;
        this.kernelWidth = kernelWidth;
        this.kernelChannel = kernelChannel;
        this.activationFunction = activationFunction;

    }

    @Override
    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public Double[][][][] calculation(Double[][][] obj) {
        //[通道][行][列]
        Double[][][] in = obj;
        // [图片某个特征图][行][列]
        Double[][][][] result = new Double[kernels.length][in.length - kernelChannel + 1][in[0].length - kernelHeight + 1][in[0][0].length - kernelWidth + 1];
        // 卷积核数量
        for (int m = 0; m < kernels.length; m++) {
            // 图中卷积核走的通道数
            for (int n = 0; n < in.length - kernelChannel + 1; n++) {
                // 图中卷积核走的行数
                for (int i = 0; i < in.length - kernelHeight + 1; i++) {
                    // 图中卷积核走的列数
                    for (int j = 0; j < in[i].length - kernelWidth + 1; j++) {
                        result[m][n][i][j] = 0.0;
                        int count = 0;
                        // 卷积核第o通道
                        for (int o = 0; o < kernelChannel; o++) {
                            // 卷积核第k行
                            for (int k = 0; k < kernelHeight; k++) {
                                // 卷积核第l列
                                for (int l = 0; l < kernelWidth; l++) {
                                    result[m][n][i][j] += kernels[m][o][k][l] * in[n + o][i + k][j + l];
                                    count++;
                                }
                            }
                        }
                        result[m][n][i][j] = activationFunction.forward(result[m][n][i][j]);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object reverse(Double inData) {
        return null;
    }

    @Override
    public void initWeight(Double minWeight, Double maxWeight) {
        for (int i = 0; i < kernels.length; i++) {
            for (int l = 0; l < kernelChannel; l++) {
                for (int j = 0; j < kernelHeight; j++) {
                    for (int k = 0; k < kernelWidth; k++) {
                        kernels[i][l][j][k] = Math.random() * (maxWeight - minWeight) + minWeight;
                    }
                }
            }
        }
    }

    @Override
    public void initWeight(Double[] doubles) {
        assert doubles.length == kernelHeight * kernels.length * kernelWidth;
        int index = 0;
        for (int i = 0; i < kernels.length; i++) {
            for (int l = 0; l < kernelChannel; l++) {
                for (int j = 0; j < kernelHeight; j++) {
                    for (int k = 0; k < kernelWidth; k++) {
                        kernels[i][l][j][k] = doubles[index++];
                    }
                }
            }
        }

    }

    @Override
    public Integer getInSize() {
        return network.getBatchSize();
    }


    @Override
    public Integer getOutSize() {
        return kernels.length;
    }

    @Override
    public void setNeuronSize(Integer size) {
        kernels = new Double[size][][][];
    }
}
