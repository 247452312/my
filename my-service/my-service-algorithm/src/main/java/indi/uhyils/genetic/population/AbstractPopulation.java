package indi.uhyils.genetic.population;

import indi.uhyils.genetic.core.EnvRule;
import indi.uhyils.genetic.core.Food;
import indi.uhyils.genetic.core.Individual;
import indi.uhyils.genetic.core.Population;
import indi.uhyils.genetic.env.EnvRuleFactory;
import indi.uhyils.genetic.individual.IndividualFactory;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月22日 08时50分
 */
public abstract class AbstractPopulation implements Population {

    /**
     * 种群是否初始化
     */
    protected final AtomicBoolean init = new AtomicBoolean(false);
    /**
     * 生存资源,此生存资源在每一代都是不变的,所以,适应度高的总能获取更多的资源,使得可以更好的存活下来,规定获得生存资源多与1的生物就可以存活下来,少于一的将获得神圣的死亡
     */
    protected final Integer survivalResources = 1000;

    /**
     * 及格比例
     */
    protected final double passRate = 1.0 / survivalResources;
    /**
     * 种群中的个体
     */
    protected Individual[] individuals;
    /**
     * 种群数量
     */
    protected int count;
    /**
     * 自然选择函数
     */
    protected EnvRule envRule;
    /**
     * 淘汰时记录每一个个体的环境适应度,为什么写在这里: 空间重用,省时
     * 自然选择不选用末位淘汰,选用在生存期间进行淘汰,减少计算时间
     */
    protected List<Double> eliminateTarget;

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
            return false;
        }
        /*资源分配式淘汰*/
        int sumElement = 0;
        for (int i = 0; i < individuals.length; i++) {
            // 在这个训练集中的得分
            double sum = 0;
            // 训练集得分项的个数
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
            double element = sum / targetCount;
            eliminateTarget.add(i, element);
            sumElement += element;
        }
        List<Individual> survivalIndividual = new ArrayList<>();
        for (int i = 0; i < eliminateTarget.size(); i++) {
            double getSourcePro = eliminateTarget.get(i) / sumElement;
            // 如果达到了及格比例,即获得了足够的食物,则不会消亡
            if (getSourcePro >= passRate) {
                survivalIndividual.add(individuals[i]);
            }
        }
        individuals = survivalIndividual.toArray(new Individual[0]);
        eliminateTarget.clear();
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
        for (Individual individual : individuals) {
            double nextDouble = RandomUtils.nextDouble(0, 1);
            // 小于就说明中奖了,需要变异
            if (nextDouble < probability) {
                individual.variation();
            }
        }
        return true;
    }

    @Override
    public boolean birth(double ratio) {
        List<Individual> list = Arrays.asList(this.individuals);
        Collections.shuffle(list);

        ratio = ratio > 1 ? (ratio - 1) : ratio;
        int addLength = (int) (individuals.length * ratio);
        List<Individual> childs = new ArrayList<>(addLength);
        for (int i = 0, index = 0; i < addLength; i++, index += 2) {
            if (index > list.size()) {
                index -= list.size();
            }
            Individual mather = list.get(index);
            Individual father = list.get(index + 1);
            Individual child = father.birth(mather);
            childs.add(child);

        }
        Individual[] newIndividual = new Individual[individuals.length + addLength];
        System.arraycopy(individuals, 0, newIndividual, 0, individuals.length);
        System.arraycopy(childs.toArray(new Individual[addLength]), 0, newIndividual, individuals.length, addLength);
        individuals = newIndividual;
        return true;
    }

    @Override
    public boolean iteration(Food[] foods, double probability, double ratio) {
        /*学习*/
        this.learn(foods);
        /*淘汰*/
        this.eliminate(foods);
        /*交叉*/
        this.birth(ratio);
        /*变异*/
        this.variation(probability);
        return true;
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
