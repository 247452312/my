package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.DO.RoleDO;


/**
 * 角色entity,Do转换
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时14分
 */
@Convert
public class RoleConvert extends AbstractDoConvert<Role, RoleDO> {

    @Override
    public Role doToEntity(RoleDO dO) {
        return new Role(dO);
    }
}
