package indi.uhyils.pojo.entity.base;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月19日 12时55分
 */
public interface Aggregate<T extends Comparable<T>> {

    /**
     * 获取唯一标示
     *
     * @return
     */
    T getUnique();


    /**
     * 填充唯一标示
     */
    void setUnique(T unique);

}
