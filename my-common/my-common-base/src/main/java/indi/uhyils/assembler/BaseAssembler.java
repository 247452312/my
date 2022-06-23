package indi.uhyils.assembler;

import indi.uhyils.annotation.Nullable;
import indi.uhyils.pojo.DO.base.BaseDO;
import indi.uhyils.pojo.DTO.base.IdDTO;
import indi.uhyils.pojo.DTO.base.Page;
import indi.uhyils.pojo.cqe.query.demo.Arg;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapping;

/**
 * 装配器
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 08时41分
 */
public interface BaseAssembler<DO extends BaseDO, ENTITY extends AbstractDoEntity<DO>, DTO extends IdDTO> {

    /**
     * entity转do
     *
     * @param entity
     *
     * @return
     */
    @Nullable
    default DO toDo(ENTITY entity) {
        return entity.toData().orElse(null);
    }

    /**
     * DTO转DO
     *
     * @param dto
     *
     * @return
     */
    DO toDo(DTO dto);

    /**
     * entity转DTO
     *
     * @param entity
     *
     * @return
     */
    DTO toDTO(ENTITY entity);

    /**
     * entity转DTO
     *
     * @param dO
     *
     * @return
     */
    DTO toDTO(DO dO);

    /**
     * do转entity
     *
     * @param dO
     *
     * @return
     */
    @Mapping(source = ".", target = "data")
    ENTITY toEntity(DO dO);

    /**
     * dto转entity
     *
     * @param dto
     *
     * @return
     */
    @Mapping(source = ".", target = "data")
    ENTITY toEntity(DTO dto);

    /**
     * 向DTO转换page
     *
     * @param page
     *
     * @return
     */
    Page<DTO> pageToDTO(Page<ENTITY> page);

    /**
     * 列表转DTO
     *
     * @param noPage
     *
     * @return
     */
    List<DTO> listEntityToDTO(List<ENTITY> noPage);

    /**
     * 列表转DTO
     *
     * @param list
     *
     * @return
     */
    List<DTO> listDoToDTO(List<DO> list);

    /**
     * 列表转entity
     *
     * @param dos
     *
     * @return
     */
    @Mapping(source = "list.empty", target = "list.empty.data")
    List<ENTITY> listToEntity(List<DO> dos);

    /**
     * DTOs to Entity
     *
     * @param dtos
     *
     * @return
     */
    @Mapping(source = "list.empty", target = "list.empty.data")
    List<ENTITY> listDTOToEntity(List<DTO> dtos);

    /**
     * dto to 查询参数
     *
     * @param dto
     *
     * @return
     */
    @Nullable
    List<Arg> toArgs(DTO dto);
}
