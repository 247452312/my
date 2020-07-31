package indi.uhyils.util.genetic.core;

import java.util.Random;

/**
 * 基因
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 09时28分
 */
public class Gene implements Cloneable {
    public static Random random = new Random();
    /**
     * 显性
     */
    private Boolean dominance;
    /**
     * 值
     */
    private Double value;

    public Gene(Boolean dominance, Double value) {
        this.dominance = dominance;
        this.value = value;
    }

    public Gene() {
    }

    public Boolean getDominance() {
        return dominance;
    }

    public void setDominance(Boolean dominance) {
        this.dominance = dominance;
    }

    public static Boolean getRandomBool() {
        int i = random.nextInt(2);
        if (i == 0) {
            return false;
        }
        return true;
    }

    public static Double getRandomT(Double min, Double max) {
        double v = random.nextDouble();
        return min + (max - min) * v;

    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"dominance\":")
                .append(dominance);
        if (value != null) {
            sb.append(",            \"value\":")
                    .append(value);
        }
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Object clone() {
        Gene objectGene = new Gene();
        objectGene.setValue(this.getValue());
        objectGene.setDominance(this.getDominance());
        return objectGene;
    }
}
