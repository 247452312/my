package indi.uhyils.util.genetic.core;

/**
 * 适应函数
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月31日 07时57分
 */
public interface FitnessFunction<T extends Data> {
    /**
     * 获取种群个体的适应度
     *
     * @param data 个体
     * @return 适应度
     */
    Double getFitness(T data);
}
