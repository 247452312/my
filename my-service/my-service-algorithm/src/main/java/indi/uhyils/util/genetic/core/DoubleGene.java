package indi.uhyils.util.genetic.core;

import java.util.Random;

/**
 * 一对基因
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 09时30分
 */
public class DoubleGene {

    public static Random random = new Random();

    /**
     * 第一个基因
     */
    private Gene one;

    /**
     * 第二个基因
     */
    private Gene two;

    /**
     * 创建的基因的值在t1到t2之间
     *
     * @param min
     * @param max
     *
     * @return
     */
    public static DoubleGene build(Double min, Double max) {
        DoubleGene tDoubleGene = new DoubleGene();
        tDoubleGene.setOne(new Gene(Gene.getRandomBool(), Gene.getRandomT(min, max)));
        tDoubleGene.setTwo(new Gene(Gene.getRandomBool(), Gene.getRandomT(min, max)));
        return tDoubleGene;
    }


    public Gene getOne() {
        return one;
    }

    public void setOne(Gene one) {
        this.one = one;
    }

    public Gene getTwo() {
        return two;
    }

    public void setTwo(Gene two) {
        this.two = two;
    }


    /**
     * 随机获取第一个还是第二个
     *
     * @return 获取到的基因
     */
    public Gene getRandomGene() {
        int i = random.nextInt(2);
        if (i == 0) {
            return one;
        }
        return two;
    }


    public Double get() {
        Boolean dominance = one.getDominance();
        if (dominance) {
            return one.getValue();
        } else if (two.getDominance()) {
            return two.getValue();
        } else {
            return one.getValue();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        sb.append("            \"one\":")
          .append(one);
        if (two != null) {
            sb.append(",            \"two\":")
              .append(two);
        }
        sb.append('}');
        return sb.toString();
    }
}
