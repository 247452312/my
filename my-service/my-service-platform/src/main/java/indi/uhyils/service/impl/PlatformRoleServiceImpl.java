package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PlatformRoleAssembler;
import indi.uhyils.repository.PlatformRoleRepository;
import indi.uhyils.pojo.DO.PlatformRoleDO;
import indi.uhyils.pojo.DTO.PlatformRoleDTO;
import indi.uhyils.pojo.entity.PlatformRole;
import indi.uhyils.service.PlatformRoleService;
import org.springframework.stereotype.Service;

/**
 * 角色表(PlatformRole)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Service
@ReadWriteMark(tables = {"sys_platform_role"})
public class PlatformRoleServiceImpl extends AbstractDoService<PlatformRoleDO, PlatformRole, PlatformRoleDTO, PlatformRoleRepository, PlatformRoleAssembler> implements PlatformRoleService {

    public PlatformRoleServiceImpl(PlatformRoleAssembler assembler, PlatformRoleRepository repository) {
        super(assembler, repository);
    }


}
