package indi.uhyils.service.impl;

import indi.uhyils.assembler.BaseAssembler;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.base.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.cqe.query.demo.Limit;
import indi.uhyils.pojo.cqe.query.demo.Order;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.base.BaseEntityRepository;
import indi.uhyils.service.BaseDoService;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 20时36分
 */
public abstract class AbstractDoService<DO extends BaseDO, ENTITY extends AbstractDoEntity<DO>, DTO extends IdDTO, REP extends BaseEntityRepository<DO, ENTITY>, ASSEM extends BaseAssembler<DO, ENTITY, DTO>> implements BaseDoService<DTO> {

    protected final ASSEM assem;

    protected final REP rep;

    protected AbstractDoService(ASSEM u, REP r) {
        this.assem = u;
        this.rep = r;
    }

    @Override
    public Long add(DTO dto) {
        ENTITY t = assem.toEntity(dto);
        Identifier save = rep.save(t);
        return save.getId();
    }

    @Override
    public Integer remove(Identifier id) {
        return rep.remove(id);
    }

    @Override
    public Integer remove(List<Arg> args) {
        return rep.remove(args, null);
    }

    @Override
    public Page<DTO> query(List<Arg> args, Order order, Limit limit) {
        Page<ENTITY> tPage = rep.find(args, order, limit);
        return assem.pageToDTO(tPage);
    }

    @Override
    public List<DTO> query(List<Identifier> ids) {
        List<ENTITY> entities = rep.find(ids);
        return assem.listEntityToDTO(entities);
    }

    @Override
    public List<DTO> queryNoPage(List<Arg> args, Order order) {
        List<ENTITY> noPage = rep.findNoPage(args, order);
        return assem.listEntityToDTO(noPage);
    }

    @Override
    public DTO query(Identifier id) {
        ENTITY entity = rep.find(id);
        return assem.toDTO(entity);
    }

    @Override
    public Integer update(DTO dto, List<Arg> args) {
        ENTITY entity = assem.toEntity(dto);
        return rep.change(entity, args);
    }

    @Override
    public Long count(List<Arg> args) {
        return rep.count(args);
    }

}
