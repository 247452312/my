package indi.uhyils.pojo.entity.base;

import java.util.Optional;

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
    Optional<T> getUnique();


    /**
     * 填充唯一标示
     */
    void setUnique(T unique);

}
