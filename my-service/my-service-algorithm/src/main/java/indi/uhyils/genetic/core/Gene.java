package indi.uhyils.genetic.core;

/**
 * 基因
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月20日 16时43分
 */
public interface Gene extends Cloneable {
    /**
     * 克隆
     *
     * @return
     */
    Gene clone();

    /**
     * 变异
     *
     * @return
     */
    boolean variation();
}
