package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.DO.RoleDO;
import indi.uhyils.repository.DeptRepository;
import indi.uhyils.repository.PowerRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 17时37分
 */
public class Role extends AbstractDoEntity<RoleDO> {

    public Role(RoleDO roleDO) {
        super(roleDO);
    }


    /**
     * 填充部门与权限
     *
     * @param deptRepository
     * @param powerRepository
     */
    public void initDept(DeptRepository deptRepository, PowerRepository powerRepository) {
        if (isEmpty()) {
            return;
        }
        List<Dept> depts = deptRepository.findByRoleId(new Identifier(getData().getId()));
        getData().setDepts(depts.stream().peek(t -> t.initPower(powerRepository)).map(AbstractDoEntity::toDo).collect(Collectors.toList()));
    }
}
