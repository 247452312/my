package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.entity.RoleDept;

/**
 * 角色(Role)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分57秒
 */
@Assembler
public class RoleAssembler extends AbstractAssembler<RoleDO, Role, RoleDTO> {

    @Override
    public Role toEntity(RoleDO dO) {
        return new Role(dO);
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        return new Role(toDo(dto));
    }

    @Override
    protected Class<RoleDO> getDoClass() {
        return RoleDO.class;
    }

    @Override
    protected Class<RoleDTO> getDtoClass() {
        return RoleDTO.class;
    }

    public RoleDept RoleDeptToEntity(RoleDeptDO t) {
        return new RoleDept(t);
    }
}

