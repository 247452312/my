package indi.uhyils.service.impl;

import indi.uhyils.assembler.BaseAssembler;
import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DTO.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.command.AddCommand;
import indi.uhyils.pojo.cqe.command.ChangeCommand;
import indi.uhyils.pojo.cqe.command.IdCommand;
import indi.uhyils.pojo.cqe.command.RemoveCommand;
import indi.uhyils.pojo.cqe.query.BaseQuery;
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
public abstract class AbstractDoService<DO extends BaseDoDO, ENTITY extends AbstractDoEntity<DO>, DTO extends IdDTO, REP extends BaseEntityRepository<DO, ENTITY>, ASSEMB extends BaseAssembler<DO, ENTITY, DTO>> implements BaseDoService<DTO> {

    protected final ASSEMB assem;

    protected final REP rep;

    public AbstractDoService(ASSEMB u, REP r) {
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
        int remove = rep.remove(new Identifier(id.getId()));
        return remove;
    }

    @Override
    public Integer remove(RemoveCommand removeCommand) {
        int remove = rep.remove(removeCommand.getOrder());
        return remove;
    }

    @Override
    public Page<DTO> query(BaseQuery order) {
        Page<ENTITY> tPage = rep.find(order);
        Page<DTO> result = assem.pageToDTO(tPage);
        return result;
    }

    @Override
    public List<DTO> query(IdsQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        List<DTO> dtos = assem.listToDTO(noPage);
        return dtos;
    }

    @Override
    public List<DTO> queryNoPage(BaseQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        List<DTO> dtos = assem.listToDTO(noPage);
        return dtos;
    }

    @Override
    public DTO query(Identifier id) {
        ENTITY entity = rep.find(id);
        DTO dto = assem.toDTO(entity);
        return dto;
    }

    @Override
    public Integer update(ChangeCommand<DTO> changeCommand) {
        ENTITY entity = assem.toEntity(changeCommand.getDto());
        int change = rep.change(entity, changeCommand.getOrder());
        return change;
    }

    @Override
    public Integer count(BaseQuery order) {
        int count = rep.count(order);
        return count;
    }

}
