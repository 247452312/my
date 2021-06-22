package indi.uhyils.genetic.population;

import indi.uhyils.genetic.core.EnvRule;
import indi.uhyils.genetic.core.Food;
import indi.uhyils.genetic.core.Individual;
import indi.uhyils.genetic.core.Population;
import indi.uhyils.genetic.env.EnvRuleFactory;
import indi.uhyils.genetic.individual.IndividualFactory;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 08时50分
 */
public abstract class AbstractPopulation implements Population {

    /**
     * 种群中的个体
     */
    private Individual[] individuals;

    /**
     * 种群数量
     */
    private int count;

    /**
     * 种群是否初始化
     */
    private AtomicBoolean init = new AtomicBoolean(false);

    /**
     * 自然选择函数
     */
    private EnvRule envRule;
    /**
     * 淘汰时记录每一个个体的环境适应度,为什么写在这里: 空间重用,省时
     */
    private List<Double> eliminateTarget;

    @Override
    public boolean init(Integer[] factors) {
        if (Boolean.FALSE.equals(init.get())) {
            individuals = new Individual[count];
            for (int i = 0; i < count; i++) {
                individuals[i] = IndividualFactory.createIndividual(factors, i);
            }
            eliminateTarget = new ArrayList<>(count);
            envRule = EnvRuleFactory.createEnvRule();
            init.set(true);
        }
        return true;
    }

    @Override
    public void setIndividualCount(int count) {
        this.count = count;
    }

    @Override
    public boolean eliminate(Food[] foods) {
        if (foods == null || foods.length == 0 || !init.get()) {
            return true;
        }
        for (int i = 0; i < individuals.length; i++) {
            double sum = 0;
            int targetCount = 0;
            for (int j = 0; j < foods.length; j++) {
                //前向计算
                double[] forward = individuals[i].forward(foods[j]);
                // 获取生存系数
                double[] survivalCoefficient = envRule.getSurvivalCoefficient(forward, foods[i].getShould());
                for (double v : survivalCoefficient) {
                    sum += v;
                    targetCount++;
                }
            }
            eliminateTarget.add(i, sum / targetCount);
        }
        return true;
    }

    @Override
    public boolean learn(Food[] foods) {
        for (Individual individual : individuals) {
            boolean learn = individual.learn(foods);
            if (!learn) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean variation(double probability) {
        return false;
    }

    @Override
    public boolean birth(double ratio) {
        return false;
    }

    @Override
    public boolean iteration(Food[] foods, double probability, double ratio) {
        return false;
    }

    @Override
    public Individual getOne() {
        int index = RandomUtils.nextInt(0, count);
        return individuals[index];
    }

    @Override
    public Individual[] getAll() {
        return individuals;
    }
}
