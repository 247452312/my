package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeptMenuDO;
import indi.uhyils.pojo.DTO.DeptMenuDTO;
import indi.uhyils.pojo.entity.DeptMenu;

/**
 * 部门-菜单关联(DeptMenu)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分25秒
 */
@Assembler
public class DeptMenuAssembler extends AbstractAssembler<DeptMenuDO, DeptMenu, DeptMenuDTO> {

    @Override
    public DeptMenu toEntity(DeptMenuDO dO) {
        return new DeptMenu(dO);
    }

    @Override
    public DeptMenu toEntity(DeptMenuDTO dto) {
        return new DeptMenu(toDo(dto));
    }

    @Override
    protected Class<DeptMenuDO> getDoClass() {
        return DeptMenuDO.class;
    }

    @Override
    protected Class<DeptMenuDTO> getDtoClass() {
        return DeptMenuDTO.class;
    }
}

