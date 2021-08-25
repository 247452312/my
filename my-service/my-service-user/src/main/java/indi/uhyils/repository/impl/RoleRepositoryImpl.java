package indi.uhyils.repository.impl;

import indi.uhyils.annotation.Repository;
import indi.uhyils.repository.convert.RoleConvert;
import indi.uhyils.dao.RoleDao;
import indi.uhyils.pojo.entity.Role;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.repository.RoleRepository;
import indi.uhyils.repository.base.AbstractRepository;


/**
 * 仓库
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时27分
 */
@Repository
public class RoleRepositoryImpl extends AbstractRepository<Role, RoleDO, RoleDao> implements RoleRepository {


    protected RoleRepositoryImpl(RoleConvert convert, RoleDao dao) {
        super(convert, dao);
    }
}
