package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.assembler.PlatformRoleAssembler;
import indi.uhyils.dao.PlatformRoleDao;
import indi.uhyils.pojo.entity.PlatformRole;
import indi.uhyils.pojo.DO.PlatformRoleDO;
import indi.uhyils.pojo.DTO.PlatformRoleDTO;
import indi.uhyils.repository.PlatformRoleRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 角色表(PlatformRole)表 仓库实现
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
@Repository
public class PlatformRoleRepositoryImpl extends AbstractRepository<PlatformRole, PlatformRoleDO, PlatformRoleDao, PlatformRoleDTO, PlatformRoleAssembler> implements PlatformRoleRepository {

    protected PlatformRoleRepositoryImpl(PlatformRoleAssembler convert, PlatformRoleDao dao) {
        super(convert, dao);
    }


}
