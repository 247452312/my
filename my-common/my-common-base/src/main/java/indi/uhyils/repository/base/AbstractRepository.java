package indi.uhyils.repository.base;

import indi.uhyils.convert.BaseEntityDOConvert;
import indi.uhyils.dao.base.DefaultDao;
import indi.uhyils.entity.HaveIdEntity;
import indi.uhyils.entity.query.BaseOrder;
import indi.uhyils.entity.query.Limit;
import indi.uhyils.entity.type.Identifier;
import indi.uhyils.pojo.model.base.BaseDoDO;
import indi.uhyils.pojo.request.model.Arg;
import indi.uhyils.pojo.response.base.Page;
import indi.uhyils.util.AssertUtil;
import java.util.List;
import java.util.stream.Collectors;


/**
 * repository模板
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月22日 15时52分
 */
public abstract class AbstractRepository<EN extends HaveIdEntity, DO extends BaseDoDO, DAO extends DefaultDao<DO>> implements BaseRepository<EN> {

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

    @Override
    public EN find(Identifier id) {
        Long idValue = id.getId();
        DO byId = dao.getById(idValue);
        return convert.doToEntity(byId);
    }

    @Override
    public List<EN> findNoPage(BaseOrder order) {
        List<Arg> args = order.args();
        Limit limit = order.limit();
        List<DO> result;
        if (limit.getPage()) {
            result = dao.getByArgs(args, limit.getNumber(), limit.getSize());
        } else {
            result = dao.getByArgsNoPage(args);
        }
        return result.stream().map(convert::doToEntity).collect(Collectors.toList());
    }

    @Override
    public Page<EN> find(BaseOrder order) {
        List<EN> noPageData = findNoPage(order);
        int count = dao.countByArgs(order.args());
        return Page.build(noPageData, order.limit(), count);
    }

    @Override
    public int remote(List<EN> ids) {
        AssertUtil.assertTrue(ids.stream().allMatch(HaveIdEntity::haveId), "删除时没有id");
        List<DO> doList = ids.stream().map(convert::entityToDo).peek(BaseDoDO::changeToDelete).peek(BaseDoDO::preUpdate).collect(Collectors.toList());
        return doList.stream().mapToInt(dao::update).sum();
    }

    protected BaseEntityDOConvert<EN, DO> getConvert() {
        return convert;
    }

    protected DAO getDao() {
        return dao;
    }

}
