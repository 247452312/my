package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.DeviceArg;
import indi.uhyils.pojo.DO.DeviceArgDO;

/**
 * 设备参数表(DeviceArg)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分30秒
 */
@Convert
public class DeviceArgConvert extends AbstractDoConvert<DeviceArg, DeviceArgDO> {

    @Override
    public DeviceArg doToEntity(DeviceArgDO dO) {
        return new DeviceArg(dO);
    }
}
