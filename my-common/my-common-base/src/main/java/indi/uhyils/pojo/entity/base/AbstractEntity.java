package indi.uhyils.pojo.entity.base;

import java.util.Optional;

/**
 * entity不是一个可序列化的逻辑集合,真正的OOP中的对象
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 14时55分
 */
public abstract class AbstractEntity<T extends Comparable<T>> implements BaseEntity<T> {

    /**
     * id 是可以没有的
     */
    public T unique;

    protected AbstractEntity() {
    }

    protected AbstractEntity(T unique) {
        this.unique = unique;
    }

    @Override
    public Optional<T> getUnique() {
        return Optional.ofNullable(unique);
    }

    @Override
    public void setUnique(T unique) {
        this.unique = unique;
    }
}

