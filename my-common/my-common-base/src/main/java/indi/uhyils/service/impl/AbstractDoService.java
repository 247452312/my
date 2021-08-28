package indi.uhyils.service.impl;

import indi.uhyils.assembler.BaseAssembler;
import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.DTO.response.base.ServiceResult;
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
public abstract class AbstractDoService<DO extends BaseDoDO, ENTITY extends AbstractDoEntity<DO>, DTO extends BaseDbDTO, REP extends BaseEntityRepository<ENTITY>, ASSEM extends BaseAssembler<DO, ENTITY, DTO>> implements BaseDoService<DTO> {

    protected final ASSEM assem;

    protected final REP rep;

    public AbstractDoService(ASSEM u, REP r) {
        this.assem = u;
        this.rep = r;
    }

    @Override
    public ServiceResult<Long> add(AddCommand<DTO> addCommand) {
        DTO dto = addCommand.getDto();
        ENTITY t = assem.toEntity(dto);
        Identifier save = rep.save(t);
        return ServiceResult.buildSuccessResult(save.getId());
    }

    @Override
    public ServiceResult<Integer> remove(IdCommand id) {
        int remove = rep.remove(new Identifier(id.getId()));
        return ServiceResult.buildSuccessResult(remove);
    }

    @Override
    public ServiceResult<Integer> remove(RemoveCommand removeCommand) {
        int remove = rep.remove(removeCommand.getOrder());
        return ServiceResult.buildSuccessResult(remove);
    }

    @Override
    public ServiceResult<Page<DTO>> query(BaseQuery order) {
        Page<ENTITY> tPage = rep.find(order);
        Page<DTO> result = assem.pageToDTO(tPage);
        return ServiceResult.buildSuccessResult(result);
    }

    @Override
    public ServiceResult<List<DTO>> query(IdsQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        List<DTO> dtos = assem.listToDTO(noPage);
        return ServiceResult.buildSuccessResult(dtos);
    }

    @Override
    public ServiceResult<List<DTO>> queryNoPage(BaseQuery order) {
        List<ENTITY> noPage = rep.findNoPage(order);
        List<DTO> dtos = assem.listToDTO(noPage);
        return ServiceResult.buildSuccessResult(dtos);
    }

    @Override
    public ServiceResult<DTO> query(Identifier id) {
        ENTITY entity = rep.find(id);
        DTO dto = assem.toDTO(entity);
        return ServiceResult.buildSuccessResult(dto);
    }

    @Override
    public ServiceResult<Integer> update(ChangeCommand<DTO> changeCommand) {
        ENTITY entity = assem.toEntity(changeCommand.getDto());
        int change = rep.change(entity, changeCommand.getOrder());
        return ServiceResult.buildSuccessResult(change);
    }

    @Override
    public ServiceResult<Integer> count(BaseQuery order) {
        int count = rep.count(order);
        return ServiceResult.buildSuccessResult(count);
    }

}
