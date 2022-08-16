package indi.uhyils.pojo.entity.base;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.BeanUtil;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时59分
 */
public abstract class AbstractDoEntity<T extends BaseDO> extends AbstractEntity<Identifier> implements DoEntity<T> {

    /**
     * 对应数据库DO
     */
    protected T data;

    /**
     * 数据库是否被修改
     */
    private boolean canUpdate;

    protected AbstractDoEntity(T t) {
        super(new Identifier(t.getId()));
        this.data = t;
        this.canUpdate = false;
    }

    protected AbstractDoEntity() {
        super();
    }

    protected AbstractDoEntity(Identifier id, T t) {
        super(id);
        this.data = t;
        this.data.setId(id.getId());
    }

    protected AbstractDoEntity(Long id, T t) {
        super(new Identifier(id));
        this.data = t;
        this.data.setId(id);
    }

    public <DO extends T, EN extends AbstractDoEntity<DO>> void completion(BaseEntityRepository<DO, EN> repository) {
        Asserts.assertTrue(this.unique != null, "数据库id不存在 不能补全");
        AbstractDoEntity<DO> dictItem = repository.find(this);
        Asserts.assertTrue(dictItem != null, "补全出的结果为空");
        final Optional<DO> data = dictItem.toData();
        data.ifPresent(t -> {
            this.data = t;
            setUnique(new Identifier(t.getId()));
        });
    }

    /**
     * 转换为DO
     *
     * @return
     */
    @Override
    public Optional<T> toData() {
        return Optional.of(data);
    }

    /**
     * 升级do中的id为id
     */
    public void upId() {
        final Optional<T> t = toData();
        Asserts.assertTrue(t.isPresent(), "领域不存在数据(data)");
        this.unique = new Identifier(t.get().getId());
    }

    public <EN extends AbstractDoEntity<T>> void saveSelf(BaseEntityRepository<T, EN> repository) {
        repository.save((EN) this);
    }

    public boolean canUpdate() {
        return canUpdate;
    }

    public void onUpdate() {
        this.canUpdate = true;
    }

    public void perUpdate() {
        data.preUpdate();
    }

    public void perInsert() {
        data.preInsert();
    }

    /**
     * 从另一个entity中复制来
     *
     * @param entity
     */
    protected void copyOf(DoEntity<T> entity) {
        final Optional<T> target = entity.toData();
        target.ifPresent(t -> BeanUtil.copyProperties(t, this.data));
        final Optional<Identifier> unique = entity.getUnique();
        unique.ifPresent(t -> this.unique = t);
    }    @Override
    public boolean haveId() {
        return unique != null && unique.getId() != null && unique.getId() > 0;
    }

    @Override
    public boolean notHaveId() {
        return !haveId();
    }



}
