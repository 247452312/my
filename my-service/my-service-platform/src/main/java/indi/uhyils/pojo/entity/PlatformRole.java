package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.PlatformRoleDO;
import indi.uhyils.repository.PlatformRoleRepository;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.annotation.Default;

/**
 * 角色表(PlatformRole)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年10月18日 19时06分09秒
 */
public class PlatformRole extends AbstractDoEntity<PlatformRoleDO> {

    @Default
    public PlatformRole(PlatformRoleDO data) {
        super(data);
    }

    public PlatformRole(Long id) {
        super(id, new PlatformRoleDO());
    }

    public PlatformRole(Long id, PlatformRoleRepository rep) {
        super(id, new PlatformRoleDO());
        completion(rep);
    }

    public PlatformRole(Identifier id) {
        super(id, new PlatformRoleDO());
    }

}
