package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Power;
import indi.uhyils.pojo.DO.PowerDO;


/**
 * 权限entity,Do转换
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月24日 18时14分
 */
@Convert
public class PowerConvert extends AbstractDoConvert<Power, PowerDO> {

    @Override
    public Power doToEntity(PowerDO dO) {
        return new Power(dO);
    }
}
