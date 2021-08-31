package indi.uhyils.service.impl;

import indi.uhyils.assembler.BaseAssembler;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseArgQuery;
import indi.uhyils.pojo.cqe.query.IdsQuery;
import indi.uhyils.pojo.entity.AbstractDoEntity;
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

    public AbstractDoService(ASSEM u, REP r) {
        this.assem = u;
        this.rep = r;
    }

    @Override
    public Long add(AddCommand<DTO> addCommand) {
        DTO dto = addCommand.getDto();
        ENTITY t = assem.toEntity(dto);
        Identifier save = rep.save(t);
        return save.getId();
    }

    @Override
    public Integer remove(IdCommand id) {
        return rep.remove(new Identifier(id.getId()));
    }

    @Override
    public Integer remove(RemoveCommand removeCommand) {
        return rep.remove(removeCommand.getOrder());
    }

    @Override
    public Page<DTO> query(BaseArgQuery order) {
        Page<ENTITY> tPage = rep.find(order);
        return assem.pageToDTO(tPage);
    }

    @Override
    public List<DTO> query(IdsQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        return assem.listEntityToDTO(noPage);
    }

    @Override
    public List<DTO> queryNoPage(BaseArgQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        return assem.listEntityToDTO(noPage);
    }

    @Override
    public DTO query(Identifier id) {
        ENTITY entity = rep.find(id);
        return assem.toDTO(entity);
    }

    @Override
    public Integer update(ChangeCommand<DTO> changeCommand) {
        ENTITY entity = assem.toEntity(changeCommand.getDto());
        return rep.change(entity, changeCommand.getOrder());
    }

    @Override
    public Integer count(BaseArgQuery order) {
        return rep.count(order);
    }

}
