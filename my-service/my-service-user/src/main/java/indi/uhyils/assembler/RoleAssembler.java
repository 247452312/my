package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.pojo.DO.RoleDeptDO;
import indi.uhyils.pojo.DTO.RoleDTO;
import indi.uhyils.pojo.entity.Role;
import org.mapstruct.Mapper;

/**
 * 角色(Role)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时32分57秒
 */
@Mapper(componentModel = "spring")
public abstract class RoleAssembler extends AbstractAssembler<RoleDO, Role, RoleDTO> {

    public Role RoleDeptToEntity(RoleDeptDO t) {
        return new Role(t);
    }
}

