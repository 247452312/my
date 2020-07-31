package indi.uhyils.util.genetic.quadratic;

import indi.uhyils.util.genetic.GeneticAlgorithm;
import indi.uhyils.util.genetic.core.*;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 09时25分
 */
public class Demo {
    public static void main(String[] args) {

        AbstractsInitialization quadraticDataDataInitialization = new AbstractsInitialization() {


            @Override
            public Double getDistance(DnaData one, DnaData tow) {
                Double a1 = one.getA();
                Double b1 = one.getB();

                Double a2 = tow.getA();
                Double b2 = tow.getB();
                double v = (a1 - a2) * (a1 - a2) - (b1 - b2) * (b1 - b2);
                return Math.abs(v);
            }

            @Override
            public void variation(DnaData data, Double mutationProbability) {
                double v = random.nextDouble();
                // 说明中了 要变异
                if (v < mutationProbability) {
                    List<DoubleGene> genes = data.getGenes();
                    int i = random.nextInt(8);
                    switch (i) {
                        // 变a基因对的第一个基因的性状
                        case 0:
                            genes.get(0).getOne().setDominance(Gene.getRandomBool());
                            break;
                        // 变a基因对的第一个基因的值
                        case 1:
                            genes.get(0).getOne().setValue(Gene.getRandomT(range[0], range[1]));
                            break;
                        // 变a基因对的第二个基因的性状
                        case 2:
                            genes.get(0).getTwo().setDominance(Gene.getRandomBool());
                            break;
                        // 变a基因对的第二个基因的值
                        case 3:
                            genes.get(0).getTwo().setValue(Gene.getRandomT(range[2], range[3]));
                            break;
                        // 变b基因对的第一个基因的性状
                        case 4:
                            genes.get(1).getOne().setDominance(Gene.getRandomBool());
                            break;
                        // 变b基因对的第一个基因的值
                        case 5:
                            genes.get(1).getOne().setValue(Gene.getRandomT(range[0], range[1]));
                            break;
                        // 变b基因对的第二个基因的性状
                        case 6:
                            genes.get(1).getTwo().setDominance(Gene.getRandomBool());
                            break;
                        // 变b基因对的第二个基因的值
                        case 7:
                            genes.get(1).getTwo().setValue(Gene.getRandomT(range[2], range[3]));
                            break;
                        default:
                    }
                }
            }
        };
        // 尝试一个拟合二次函数
        FitnessFunction<DnaData> quadraticDataFitnessFunction = data -> {
            // 目标函数是 a^2 + b = 0
            List<DoubleGene> genes = data.getGenes();
            Double a = genes.get(0).get();
            Double b = genes.get(1).get();
            return Math.abs(a * a + b);
        };
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(1500, 0.05, quadraticDataFitnessFunction, quadraticDataDataInitialization, 0.5, 0.1);
        geneticAlgorithm.init(0.01, -100.0, 100.0, -10000.0, 10000.0);
        geneticAlgorithm.iteration(20);
        for (Data datum : geneticAlgorithm.getData()) {
            DnaData point = (DnaData) datum;
            Double a = point.getA();
            Double b = point.getB();
            System.out.println(String.format("%f,%f", a, b));
        }
    }
}
