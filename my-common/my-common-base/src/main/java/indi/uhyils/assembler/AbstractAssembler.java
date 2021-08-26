package indi.uhyils.assembler;

import indi.uhyils.pojo.DO.base.BaseDoDO;
import indi.uhyils.pojo.DTO.BaseDbDTO;
import indi.uhyils.pojo.DTO.response.base.Page;
import indi.uhyils.pojo.entity.AbstractDoEntity;
import indi.uhyils.util.BeanUtil;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时51分
 */
public abstract class AbstractAssembler<DO extends BaseDoDO, ENTITY extends AbstractDoEntity<DO>, DTO extends BaseDbDTO> implements BaseAssembler<DO, ENTITY, DTO> {

    @Override
    public DO toDo(ENTITY entity) {
        return entity.toDo();
    }

    @Override
    public DO toDo(DTO dto) {
        return BeanUtil.copyProperties(dto, getDoClass());
    }

    @Override
    public DTO toDTO(ENTITY entity) {
        return BeanUtil.copyProperties(entity.toDo(), getDtoClass());
    }

    @Override
    public DTO toDTO(DO dO) {
        return BeanUtil.copyProperties(dO, getDtoClass());
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
    public List<DTO> listToDTO(List<ENTITY> noPage) {
        return noPage.stream().map(this::toDTO).collect(Collectors.toList());
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
