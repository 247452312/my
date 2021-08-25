package indi.uhyils.service.impl;

import indi.uhyils.pojo.DO.base.BaseIdDO;
import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseRepository;
import indi.uhyils.service.BaseDoService;
import indi.uhyils.util.DtoEntityConvertUtil;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时36分
 */
public abstract class AbstractDoService<Y extends BaseIdDO, T extends AbstractDoEntity<Y>, E extends BaseDbDTO, R extends BaseRepository<T>> implements BaseDoService<E> {

    @Override
    public Identifier add(AddCommand addCommand) {
        BaseRepository<T> repository = getRepository();
        T t = DtoEntityConvertUtil.toEntity(addCommand, getDoClass(), getEntityClass());
        return repository.save(t);
    }


    @Override
    public int remove(Identifier id) {
        BaseRepository<T> repository = getRepository();
        return repository.remove(id);
    }

    @Override
    public int remove(RemoveCommand removeCommand) {
        return 0;
    }

    @Override
    public Page<E> query(BaseQuery order) {
        return null;
    }

    @Override
    public List<E> query(IdsQuery order) {
        return null;
    }

    @Override
    public List<E> queryNoPage(BaseQuery order) {
        return null;
    }

    @Override
    public E query(Identifier id) {
        return null;
    }

    @Override
    public int update(ChangeCommand<E> changeCommand) {
        return 0;
    }

    @Override
    public Integer count(BaseQuery order) {
        return 0;
    }

    /**
     * 获取目标DO的class
     *
     * @return
     */
    protected abstract Class<Y> getDoClass();

    /**
     * 获取目标的EntityClass
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();

    /**
     * 获取数据仓库
     *
     * @return
     */
    protected abstract BaseRepository<T> getRepository();

}
