package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.entity.type.Identifier;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时59分
 */
public abstract class AbstractDoEntity<T extends BaseDoDO> extends AbstractEntity {

    /**
     * 对应数据库DO
     */
    protected final T data;

    protected AbstractDoEntity(T t) {
        super();
        this.id = new Identifier(t.getId());
        this.data = t;
    }

    /**
     * 给子类用
     *
     * @return
     */
    protected T getData() {
        return data;
    }

    /**
     * 转换为DO
     *
     * @return
     */
    public T toDo() {
        return data;
    }


    public void perUpdate() {
        data.preUpdate();
    }

    public void perInsert() {
        data.preInsert();
    }

}