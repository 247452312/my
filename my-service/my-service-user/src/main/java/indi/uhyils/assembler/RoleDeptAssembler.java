package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.RoleDeptDTO;
import indi.uhyils.pojo.entity.RoleDept;

/**
 * 角色-部门关联图(RoleDept)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时33分01秒
 */
@Assembler
public class RoleDeptAssembler extends AbstractAssembler<RoleDeptDO, RoleDept, RoleDeptDTO> {

    @Override
    public RoleDept toEntity(RoleDeptDO dO) {
        return new RoleDept(dO);
    }

    @Override
    public RoleDept toEntity(RoleDeptDTO dto) {
        return new RoleDept(toDo(dto));
    }

    @Override
    protected Class<RoleDeptDO> getDoClass() {
        return RoleDeptDO.class;
    }

    @Override
    protected Class<RoleDeptDTO> getDtoClass() {
        return RoleDeptDTO.class;
    }
}

