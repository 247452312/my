package indi.uhyils.repository.base;

import indi.uhyils.assembler.AbstractAssembler;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.BaseArgQuery;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.HaveIdEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.util.AssertUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * repository模板
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时52分
 */
public abstract class AbstractRepository<EN extends AbstractDoEntity<DO>, DO extends BaseDO, DAO extends DefaultDao<DO>, DTO extends IdDTO, ASSEM extends AbstractAssembler<DO, EN, DTO>> implements BaseEntityRepository<DO, EN> {

    /**
     * 转换器
     */
    protected final ASSEM assembler;

    protected final DAO dao;

    protected AbstractRepository(ASSEM convert, DAO dao) {
        this.assembler = convert;
        this.dao = dao;
    }

    @Override
    public Identifier save(EN entity) {
        if (entity.notHaveId()) {
            DO aDo = assembler.toDo(entity);
            aDo.preInsert();
            dao.insert(aDo);
            return Identifier.build(aDo.getId());
        }
        boolean canUpdate = entity.canUpdate();
        if (!canUpdate) {
            DO aDo = assembler.toDo(entity);
            return Identifier.build(aDo.getId());
        }
        DO aDo = assembler.toDo(entity);
        aDo.preUpdate();
        dao.update(aDo);
        return Identifier.build(aDo.getId());
    }

    @Override
    public List<Identifier> save(List<EN> entities) {
        return entities.stream().map(this::save).collect(Collectors.toList());
    }


    @Override
    public <E extends HaveIdEntity> EN find(E query) {
        AssertUtil.assertTrue(query.haveId(), "根据id查询, 你id不存在?");
        Identifier id = query.getId();
        return find(id);
    }

    /**
     * 根据id查询
     *
     * @param ids 主键ids
     *
     * @return
     */
    @Override
    public <E extends Identifier> List<EN> find(List<E> ids) {
        List<Long> idList = ids.stream().map(t -> t.getId()).collect(Collectors.toList());
        List<DO> byIds = dao.getByIds(idList);
        return byIds.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public <E extends Identifier> EN find(E id) {
        Long idValue = id.getId();
        DO byId = dao.getById(idValue);
        return assembler.toEntity(byId);
    }

    @Override
    public <E extends BaseArgQuery> List<EN> findNoPage(E order) {
        List<Arg> args = order.getArgs();
        Limit limit = order.getLimit();
        List<DO> result;
        if (limit.getPage()) {
            result = dao.getByArgs(args, order.getOrder(), order.getLimit());
        } else {
            result = dao.getByArgsNoPage(args, order.getOrder());
        }
        return result.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public <E extends BaseArgQuery> Page<EN> find(E order) {
        List<EN> noPageData = findNoPage(order);
        int count = dao.countByArgs(order.getArgs());
        return Page.build(noPageData, order.getLimit(), count);
    }

    @Override
    public int remove(List<EN> entities) {
        AssertUtil.assertTrue(entities.stream().allMatch(HaveIdEntity::haveId), "删除时没有id");
        List<DO> doList = entities.stream().map(t -> assembler.toDo(t)).peek(BaseDO::changeToDelete).peek(BaseDO::preUpdate).collect(Collectors.toList());
        return doList.stream().mapToInt(dao::update).sum();
    }

    @Override
    public <E extends Identifier> int remove(E... ids) {
        List<EN> ens = find(Arrays.asList(ids));

        List<DO> updateDos = ens.stream().peek(AbstractDoEntity::perUpdate).map(AbstractDoEntity::toDo).collect(Collectors.toList());
        return dao.updateBatch(updateDos);
    }

    @Override
    public <E extends BaseArgQuery> int remove(E order) {
        List<EN> noPage = findNoPage(order);
        return remove(noPage);
    }

    @Override
    public <E extends BaseArgQuery> int change(EN entity, E query) {
        return dao.updateByOrder(entity.toDo(), query.getArgs());
    }

    @Override
    public <E extends BaseArgQuery> int count(E order) {
        return dao.countByArgs(order.getArgs());
    }


}
