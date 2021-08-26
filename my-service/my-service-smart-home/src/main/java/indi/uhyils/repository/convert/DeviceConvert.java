package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.Device;
import indi.uhyils.pojo.DO.DeviceDO;

/**
 * 设备表(Device)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分25秒
 */
@Convert
public class DeviceConvert extends AbstractDoConvert<Device, DeviceDO> {

    @Override
    public Device doToEntity(DeviceDO dO) {
        return new Device(dO);
    }
}
