package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Dept;
import indi.uhyils.pojo.DO.DeptDO;


/**
 * 部门entity,Do转换
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时14分
 */
@Convert
public class DeptConvert extends AbstractDoConvert<Dept, DeptDO> {

    @Override
    public Dept doToEntity(DeptDO dO) {
        return new Dept(dO);
    }
}
