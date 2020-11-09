package indi.uhyils.util.genetic.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * DNA结构
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 13时46分
 */
public abstract class AbstractsInitialization implements DataInitialization<DnaData> {

    protected Random random = new Random();
    /**
     * 随机因子
     */
    protected Double[] range;
    /**
     * 多远距离算同一个点
     */
    private Double distance;

    @Override
    public Double getDistance() {
        return distance;
    }

    @Override
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public DnaData random() {
        DnaData quadraticData = new DnaData();
        ArrayList<DoubleGene> genes = new ArrayList<>();
        // 添加a
        genes.add(DoubleGene.build(range[0], range[1]));
        // 添加b
        genes.add(DoubleGene.build(range[2], range[3]));
        quadraticData.setGenes(genes);
        return quadraticData;
    }

    /**
     * 设置随机约束
     *
     * @param range 种群约束
     */
    public void setRandomFactor(Double... range) {
        this.range = range;
    }

    @Override
    public DnaData hybridization(DnaData one, DnaData otherOne) {
        List<DoubleGene> oneGenes = one.getGenes();
        List<DoubleGene> otherOneGenes = otherOne.getGenes();
        // one基因对取第ai条基因
        int ai = random.nextInt(2);
        // two基因对取第bi条基因
        int bi = random.nextInt(2);
        List<DoubleGene> result = new ArrayList<>();
        // 每一个基因只和自己杂交
        for (int i = 0; i < oneGenes.size(); i++) {
            DoubleGene oneGenesDoubleGene = oneGenes.get(i);
            DoubleGene otherOneGenesDoubleGene = otherOneGenes.get(i);
            Gene oneResult;
            Gene otherOneResult;
            if (ai == 0) {
                oneResult = (Gene) oneGenesDoubleGene.getOne().clone();
            } else {
                oneResult = (Gene) oneGenesDoubleGene.getTwo().clone();
            }
            if (bi == 0) {
                otherOneResult = (Gene) otherOneGenesDoubleGene.getOne().clone();
            } else {
                otherOneResult = (Gene) otherOneGenesDoubleGene.getTwo().clone();
            }
            DoubleGene e = new DoubleGene();
            e.setOne(oneResult);
            e.setTwo(otherOneResult);
            result.add(e);
        }
        DnaData quadraticData = new DnaData();
        quadraticData.setGenes(result);
        return quadraticData;
    }

    @Override
    public abstract Double getDistance(DnaData one, DnaData tow);

    @Override
    public abstract void variation(DnaData data, Double mutationProbability);
}
