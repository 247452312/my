package indi.uhyils.genetic.core;

/**
 * 双螺旋
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时42分
 */
public interface DoubleHelix {

    /**
     * 获取两根单螺旋
     *
     * @return
     */
    SingleHelix[] getTwoHelix();

    /**
     * 获取所有基因对
     *
     * @return
     */
    GenePair[] getAllGenePair();

    /**
     * 替换一条基因链
     *
     * @param singleHelix 基因链
     * @param index       修改的是第几条
     */
    void changeHelix(SingleHelix singleHelix, Integer index);

    /**
     * 变异
     *
     * @return
     */
    boolean variation();

    /**
     * 克隆一条基因链
     *
     * @param index 哪一条
     * @return
     */
    SingleHelix cloneHelixByIndex(Integer index);


}
