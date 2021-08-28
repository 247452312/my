package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.PowerRepository;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 14时40分
 */
public class PowerId extends Identifier {

    private Long powerId;

    public PowerId(Long powerId) {
        super(powerId);
        this.powerId = powerId;
    }


    public Long powerIdValue() {
        return powerId;
    }

    public void removeSelfLink(PowerRepository rep) {
        rep.removeDeptPowerByPowerId(this);
    }

    public void removeSelf(PowerRepository rep) {
        rep.remove(this);
    }
}
