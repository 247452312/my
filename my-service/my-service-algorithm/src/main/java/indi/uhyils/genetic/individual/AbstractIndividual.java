package indi.uhyils.genetic.individual;

import indi.uhyils.genetic.core.Food;
import indi.uhyils.genetic.core.Individual;
import indi.uhyils.genetic.core.Layer;
import indi.uhyils.genetic.layer.LayerFactory;


/**
 * 个体(神经网络)
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 08时57分
 */
public abstract class AbstractIndividual implements Individual {

    /**
     * 隐藏层
     */
    protected Layer[] middleLayer;

    /**
     * 输入层
     */
    protected Layer in;
    /**
     * 输出层
     */
    protected Layer out;

    @Override
    public Layer[] getLayers() {
        return middleLayer;
    }

    @Override
    public boolean init(Integer[] factors, Integer index, int inSize, int outSize) {
        int cursor = 0;
        this.in = LayerFactory.creatLayer(factors, index, cursor, inSize);
        cursor++;
        int middleLayerSize = getMiddleLayerSize();
        int[] middleLayerNeuronSize = getMiddleLayerNeuronSize();
        middleLayer = new Layer[middleLayerSize];
        for (int i = 0; i < middleLayerSize; i++) {
            middleLayer[i] = LayerFactory.creatLayer(factors, index, cursor, middleLayerNeuronSize[i]);
            cursor++;
        }
        this.out = LayerFactory.creatLayer(factors, index, cursor, outSize);
        return true;
    }

    /**
     * 隐藏层层数
     *
     * @return
     */
    protected abstract int getMiddleLayerSize();

    /**
     * 获取隐藏层每层神经元大小
     *
     * @return
     */
    protected abstract int[] getMiddleLayerNeuronSize();

    @Override
    public boolean learn(Food[] foods) {
        return false;
    }

    @Override
    public boolean variation() {
        return false;
    }

    @Override
    public Individual birth(Individual individual) {
        return null;
    }

    @Override
    public double[] forward(Food food) {
        return new double[0];
    }
}
