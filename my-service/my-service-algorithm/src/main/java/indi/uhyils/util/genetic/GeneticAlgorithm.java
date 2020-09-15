package indi.uhyils.util.genetic;

import indi.uhyils.util.LogUtil;
import indi.uhyils.util.genetic.core.AbstractsInitialization;
import indi.uhyils.util.genetic.core.DnaData;
import indi.uhyils.util.genetic.core.FitnessFunction;

import java.util.*;

/**
 * 遗传算法
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 07时55分
 */
public class GeneticAlgorithm {

    /**
     * 种群数量
     */
    private Integer maxNum;

    /**
     * 变异概率
     */
    private Double mutationProbability;

    /**
     * 适应函数
     */
    private FitnessFunction fitnessFunction;

    /**
     * 种群硬性约束
     */
    private AbstractsInitialization initialization;

    /**
     * 增长因子
     */
    private Double growthRate;

    /**
     * 不变异比例
     */
    private Double nonVariationRatio;

    /**
     * 种群中所有的个体
     * 数量固定为种群数量最大值的1.5倍,其中多出来的0.5用来保证杂交后多出来的个体
     */
    private List<DnaData> data;

    /**
     * 初始化种群
     *
     * @param maxNum              种群数量->种群每次迭代完成总会保持这个数量
     * @param mutationProbability 个体的变异概率 -> 每次变异时不是每一个个体都会变异
     * @param fitnessFunction     适应函数 -> 关键 决定这个个体是否适应这个世界
     * @param initialization      种群硬性约束 -> 初始化,交配,变异时将个体的值约束在一定的范围内,防止超出导致不可计算
     * @param growthRate          增长因子 -> 每次种群交配的比例
     * @param nonVariationRatio   不变异比例->最好的那一批个体不会发生变异,保存全局最优解
     */
    public GeneticAlgorithm(Integer maxNum, Double mutationProbability, FitnessFunction fitnessFunction, AbstractsInitialization initialization, Double growthRate, Double nonVariationRatio) {
        this.maxNum = maxNum;
        this.mutationProbability = mutationProbability;
        this.fitnessFunction = fitnessFunction;
        this.initialization = initialization;
        this.growthRate = growthRate;
        this.nonVariationRatio = nonVariationRatio;
    }

    /**
     * 初始化种群
     */
    public void init(Double distance, Double... doubles) {
        getInitialization().setRandomFactor(doubles);
        getInitialization().setDistance(distance);
        this.data = new ArrayList<>(maxNum);
        for (int i = 0; i < maxNum; i++) {
            data.add(initialization.random());
        }
    }

    /**
     * 迭代1次
     */
    public void iteration() {
        // 洗牌算法,打乱数组
        Collections.shuffle(data);
        // 杂交,孕育下一代并添加到数组中
        for (int i = 0; i < maxNum * growthRate; i++) {
            DnaData one = this.data.get(i * 2);
            DnaData two = this.data.get(i * 2 + 1);
            DnaData hybridization = initialization.hybridization(one, two);
            data.add(hybridization);
        }
        // 根据适应度排序  0->最适应 1-> 最不适应 从小到大排序
        Collections.sort(data, Comparator.comparing(o -> fitnessFunction.getFitness(o)));
        // 变异 最适应环境的那一批不变异
        for (int i = new Double(maxNum * nonVariationRatio).intValue(); i < data.size(); i++) {
            initialization.variation(data.get(i), mutationProbability);
        }
        // 排序
        Collections.sort(data, Comparator.comparing(o -> fitnessFunction.getFitness(o)));
        // 自然选择
        Iterator<DnaData> iterator = data.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (i > maxNum) {
                iterator.remove();
            }
            i++;
        }
    }

    /**
     * 迭代
     *
     * @param count 迭代次数100-500
     */
    public void iteration(Integer count) {
        for (int i = 0; i < count; i++) {
            iteration();
            LogUtil.info(this, "遍历第" + (i + 1) + "遍");
        }
    }


    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public Double getMutationProbability() {
        return mutationProbability;
    }

    public void setMutationProbability(Double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunction(FitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public List<DnaData> getData() {
        return data;
    }

    public void setData(List<DnaData> data) {
        this.data = data;
    }

    public AbstractsInitialization getInitialization() {
        return initialization;
    }

    public void setInitialization(AbstractsInitialization initialization) {
        this.initialization = initialization;
    }

    public Double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(Double growthRate) {
        this.growthRate = growthRate;
    }

    public Double getNonVariationRatio() {
        return nonVariationRatio;
    }

    public void setNonVariationRatio(Double nonVariationRatio) {
        this.nonVariationRatio = nonVariationRatio;
    }

    /**
     * 获取迭代后的结果
     *
     * @return 结果
     */
    public HashMap<DnaData, Integer> getResult() {
        // 排序
        Collections.sort(data, Comparator.comparing(o -> fitnessFunction.getFitness(o)));
        HashMap<DnaData, Integer> result = new HashMap<>();
        for (DnaData datum : data) {
            // 默认里面没有datum相近的点
            Boolean b = true;
            for (Map.Entry<DnaData, Integer> entity : result.entrySet()) {
                DnaData key = entity.getKey();
                Double distance = initialization.getDistance(datum, key);
                // 获取的值小于设定的值
                if (initialization.getDistance() > distance) {
                    b = false;
                    result.put(key, result.get(key) + 1);
                    break;
                }
            }
            // 遍历完成之后依旧没有相近的值
            if (b) {
                result.put(datum, 1);
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GeneticAlgorithm that = (GeneticAlgorithm) o;
        return Objects.equals(maxNum, that.maxNum) &&
                Objects.equals(mutationProbability, that.mutationProbability) &&
                Objects.equals(fitnessFunction, that.fitnessFunction) &&
                Objects.equals(initialization, that.initialization) &&
                Objects.equals(growthRate, that.growthRate) &&
                Objects.equals(nonVariationRatio, that.nonVariationRatio) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxNum, mutationProbability, fitnessFunction, initialization, growthRate, nonVariationRatio, data);
    }
}
