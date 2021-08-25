package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.pojo.DO.DeptDO;
import indi.uhyils.repository.PowerRepository;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 部门
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时14分
 */
public class Dept extends AbstractDoEntity<DeptDO> {

    public Dept(DeptDO deptDO) {
        super(deptDO);
    }

    /**
     * 填充权限
     *
     * @param powerRepository
     */
    public void initPower(PowerRepository powerRepository) {
        if (isEmpty()) {
            return;
        }
        List<Power> powers = powerRepository.findByDeptId(new Identifier(getData().getId()));
        getData().setPowers(powers.stream().map(AbstractDoEntity::toDo).collect(Collectors.toList()));
    }
}
