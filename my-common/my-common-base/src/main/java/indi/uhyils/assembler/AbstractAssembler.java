package indi.uhyils.assembler;

import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.util.BeanUtil;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时51分
 */
public abstract class AbstractAssembler<DO extends BaseDO, ENTITY extends AbstractDoEntity<DO>, DTO extends IdDTO> implements BaseAssembler<DO, ENTITY, DTO> {

    @Override
    public DO toDo(ENTITY entity) {
        return entity.toDo();
    }

    @Override
    public DO toDo(DTO dto) {
        Class<DO> doClass = getDoClass();
        return BeanUtil.copyProperties(dto, doClass);
    }

    @Override
    public DTO toDTO(ENTITY entity) {
        DO source = entity.toDo();
        Class<DTO> dtoClass = getDtoClass();
        return BeanUtil.copyProperties(source, dtoClass);
    }

    @Override
    public DTO toDTO(DO dO) {
        Class<DTO> dtoClass = getDtoClass();
        return BeanUtil.copyProperties(dO, dtoClass);
    }

    @Override
    public Page<DTO> pageToDTO(Page<ENTITY> page) {
        Page<DTO> resultPage = new Page<>();
        BeanUtil.copyProperties(page, resultPage);
        List<DTO> dtos = page.getList().stream().map(this::toDTO).collect(Collectors.toList());
        resultPage.setList(dtos);
        return resultPage;
    }

    @Override
    public List<DTO> listEntityToDTO(List<ENTITY> noPage) {
        return noPage.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DTO> listDoToDTO(List<DO> list) {
        return list.stream().map(t -> this.toDTO(t)).collect(Collectors.toList());
    }

    @Override
    public List<ENTITY> listToEntity(List<DO> dos) {
        return dos.stream().map(t -> toEntity(t)).collect(Collectors.toList());
    }

    @Override
    public List<ENTITY> listDTOToEntity(List<DTO> dtos) {
        return dtos.stream().map(t -> toEntity(t)).collect(Collectors.toList());
    }

    @Override
    public abstract ENTITY toEntity(DO dO);

    @Override
    public abstract ENTITY toEntity(DTO dto);

    /**
     * 获取do的类型
     *
     * @return
     */
    protected abstract Class<DO> getDoClass();

    /**
     * 获取dto的类型
     *
     * @return
     */
    protected abstract Class<DTO> getDtoClass();
}
