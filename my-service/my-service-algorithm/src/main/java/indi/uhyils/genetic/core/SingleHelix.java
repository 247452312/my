package indi.uhyils.genetic.core;

/**
 * 单螺旋
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时43分
 */
public interface SingleHelix extends Cloneable {

    /**
     * 获取一个基因
     *
     * @param index 基因位置
     * @return
     */
    Gene getOneGene(Integer index);

    /**
     * 获取一个基因对
     *
     * @param index 基因位置
     * @return
     */
    GenePair getOneGenePair(Integer index);

    /**
     * 克隆方法
     *
     * @return
     */
    SingleHelix clone();

    /**
     * 变异(双向变异,中庸)
     *
     * @return
     */
    boolean variation();
}
