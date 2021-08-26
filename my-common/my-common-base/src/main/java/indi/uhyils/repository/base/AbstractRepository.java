package indi.uhyils.repository.base;

import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DTO.request.model.Arg;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.cqe.query.BaseQuery;
import indi.uhyils.pojo.cqe.query.Limit;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.pojo.entity.HaveIdEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.convert.BaseEntityDOConvert;
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
public abstract class AbstractRepository<EN extends AbstractDoEntity<DO>, DO extends BaseDoDO, DAO extends DefaultDao<DO>> implements BaseRepository<EN> {

    /**
     * 转换器
     */
    private final BaseEntityDOConvert<EN, DO> convert;

    private final DAO dao;

    protected AbstractRepository(BaseEntityDOConvert<EN, DO> convert, DAO dao) {
        this.convert = convert;
        this.dao = dao;
    }

    @Override
    public Identifier save(EN entity) {
        if (entity.notHaveId()) {
            DO aDo = convert.entityToDo(entity);
            aDo.preInsert();
            dao.insert(aDo);
            return Identifier.build(aDo.getId());
        }
        boolean canUpdate = entity.canUpdate();
        if (!canUpdate) {
            DO aDo = convert.entityToDo(entity);
            return Identifier.build(aDo.getId());
        }
        DO aDo = convert.entityToDo(entity);
        aDo.preUpdate();
        dao.update(aDo);
        return Identifier.build(aDo.getId());
    }

    @Override
    public List<Identifier> save(List<EN> entities) {
        return entities.stream().map(this::save).collect(Collectors.toList());
    }


    @Override
    public EN find(HaveIdEntity query) {
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
    public List<EN> find(List<Identifier> ids) {
        String idsStr = "('" + ids.stream().map(Object::toString).collect(Collectors.joining("','")) + "')";
        ArrayList<DO> byArgsNoPage = getDao().getByArgsNoPage(Collections.singletonList(new Arg("id", "in", idsStr)), null);
        return byArgsNoPage.stream().map(convert::doToEntity).collect(Collectors.toList());
    }

    @Override
    public EN find(Identifier id) {
        Long idValue = id.getId();
        DO byId = dao.getById(idValue);
        return convert.doToEntity(byId);
    }

    @Override
    public List<EN> findNoPage(BaseQuery order) {
        List<Arg> args = order.args();
        Limit limit = order.limit();
        List<DO> result;
        if (limit.getPage()) {
            result = dao.getByArgs(args, order.order(), order.limit());
        } else {
            result = dao.getByArgsNoPage(args,order.order());
        }
        return result.stream().map(convert::doToEntity).collect(Collectors.toList());
    }

    @Override
    public Page<EN> find(BaseQuery order) {
        List<EN> noPageData = findNoPage(order);
        int count = dao.countByArgs(order.args());
        return Page.build(noPageData, order.limit(), count);
    }

    @Override
    public int remove(List<EN> ids) {
        AssertUtil.assertTrue(ids.stream().allMatch(HaveIdEntity::haveId), "删除时没有id");
        List<DO> doList = ids.stream().map(convert::entityToDo).peek(BaseDoDO::changeToDelete).peek(BaseDoDO::preUpdate).collect(Collectors.toList());
        return doList.stream().mapToInt(dao::update).sum();
    }

    @Override
    public int remove(Identifier... ids) {
        List<EN> ens = find(Arrays.asList(ids));

        List<DO> updateDos = ens.stream().peek(AbstractDoEntity::perUpdate).map(AbstractDoEntity::toDo).collect(Collectors.toList());
        return getDao().updateBatch(updateDos);
    }

    @Override
    public int remove(BaseQuery order) {
        List<EN> noPage = findNoPage(order);
        return remove(noPage);
    }

    @Override
    public int change(EN entity, BaseQuery query) {
        return getDao().updateByOrder(entity.toDo(), query.args());
    }

    @Override
    public int count(BaseQuery order) {
        return getDao().countByArgs(order.args());
    }

    protected BaseEntityDOConvert<EN, DO> getConvert() {
        return convert;
    }

    protected DAO getDao() {
        return dao;
    }

}
