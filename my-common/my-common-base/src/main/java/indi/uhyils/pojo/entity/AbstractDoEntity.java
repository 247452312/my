package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import indi.uhyils.util.BeanUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时59分
 */
public abstract class AbstractDoEntity<T extends BaseDO> extends AbstractEntity {

    /**
     * 对应数据库DO
     */
    protected final T data;

    protected AbstractDoEntity(T t) {
        super();
        this.id = new Identifier(t.getId());
        this.data = t;
    }

    protected AbstractDoEntity(Long id, T t) {
        super();
        this.id = new Identifier(id);
        this.data = t;
        this.data.setId(id);
    }

    public <DO extends BaseDO, EN extends AbstractDoEntity<DO>> void completion(BaseEntityRepository<DO, EN> repository) {
        AbstractDoEntity<DO> dictItem = repository.find(this);
        DO source = dictItem.toDo();
        BeanUtil.copyProperties(source, data);
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
