package indi.uhyils.pojo.entity;

import indi.uhyils.annotation.Default;
import indi.uhyils.pojo.DO.PowerDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.PowerRepository;


/**
 * 权限
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 08时14分
 */
public class Power extends AbstractDoEntity<PowerDO> {

    @Default
    public Power(PowerDO data) {
        super(data);
    }

    public Power(Identifier powerId) {
        super(powerId, new PowerDO());
    }


    public Power(Long id) {
        super(id, new PowerDO());
    }

    public void removeSelfLink(PowerRepository rep) {
        rep.removeDeptPowerByPowerId(this);
    }

    public void removeSelf(PowerRepository rep) {
        rep.remove(this.getUnique());
    }

}
