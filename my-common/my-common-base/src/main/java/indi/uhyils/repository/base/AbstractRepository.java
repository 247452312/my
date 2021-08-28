package indi.uhyils.repository.base;

import indi.uhyils.assembler.AbstractAssembler;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.cqe.Arg;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.Limit;
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
public abstract class AbstractRepository<EN extends AbstractDoEntity<DO>, DO extends BaseDoDO, DAO extends DefaultDao<DO>, ASSEM extends AbstractAssembler<DO, EN, ?>> implements BaseEntityRepository<EN> {

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
        String idsStr = "('" + ids.stream().map(Object::toString).collect(Collectors.joining("','")) + "')";
        ArrayList<DO> byArgsNoPage = dao.getByArgsNoPage(Collections.singletonList(new Arg("id", "in", idsStr)), null);
        return byArgsNoPage.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public <E extends Identifier> EN find(E id) {
        Long idValue = id.getId();
        DO byId = dao.getById(idValue);
        return assembler.toEntity(byId);
    }

    @Override
    public <E extends BaseQuery> List<EN> findNoPage(E order) {
        List<Arg> args = order.args();
        Limit limit = order.limit();
        List<DO> result;
        if (limit.getPage()) {
            result = dao.getByArgs(args, order.order(), order.limit());
        } else {
            result = dao.getByArgsNoPage(args, order.order());
        }
        return result.stream().map(assembler::toEntity).collect(Collectors.toList());
    }

    @Override
    public <E extends BaseQuery> Page<EN> find(E order) {
        List<EN> noPageData = findNoPage(order);
        int count = dao.countByArgs(order.args());
        return Page.build(noPageData, order.limit(), count);
    }

    @Override
    public int remove(List<EN> entitys) {
        AssertUtil.assertTrue(entitys.stream().allMatch(HaveIdEntity::haveId), "删除时没有id");
        List<DO> doList = entitys.stream().map(assembler::toDo).peek(BaseDoDO::changeToDelete).peek(BaseDoDO::preUpdate).collect(Collectors.toList());
        return doList.stream().mapToInt(dao::update).sum();
    }

    @Override
    public <E extends Identifier> int remove(E... ids) {
        List<EN> ens = find(Arrays.asList(ids));

        List<DO> updateDos = ens.stream().peek(AbstractDoEntity::perUpdate).map(AbstractDoEntity::toDo).collect(Collectors.toList());
        return dao.updateBatch(updateDos);
    }

    @Override
    public <E extends BaseQuery> int remove(E order) {
        List<EN> noPage = findNoPage(order);
        return remove(noPage);
    }

    @Override
    public <E extends BaseQuery> int change(EN entity, E query) {
        return dao.updateByOrder(entity.toDo(), query.args());
    }

    @Override
    public <E extends BaseQuery> int count(E order) {
        return dao.countByArgs(order.args());
    }


}
