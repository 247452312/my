package indi.uhyils.util.genetic.core;

import java.util.List;
import java.util.Objects;

/**
 * 环状DNA数据
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 09时27分
 */
public class DnaData implements Data {

    /**
     * 基因对们
     */
    private List<DoubleGene> genes;

    public Double getA() {
        DoubleGene doubleDoubleGene = getGenes().get(0);
        return doubleDoubleGene.get();
    }

    public List<DoubleGene> getGenes() {
        return genes;
    }

    public void setGenes(List<DoubleGene> genes) {
        this.genes = genes;
    }

    public Double getB() {
        DoubleGene doubleDoubleGene = getGenes().get(1);
        return doubleDoubleGene.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(genes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return Boolean.TRUE;
        }
        if (o == null || getClass() != o.getClass()) {
            return Boolean.FALSE;
        }
        DnaData dnaData = (DnaData) o;
        return Objects.equals(genes, dnaData.genes);
    }
}
