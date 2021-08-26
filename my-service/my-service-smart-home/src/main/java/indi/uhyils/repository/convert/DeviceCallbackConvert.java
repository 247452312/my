package indi.uhyils.repository.convert;

import indi.uhyils.annotation.Convert;
import indi.uhyils.pojo.entity.DeviceCallback;
import indi.uhyils.pojo.DO.DeviceCallbackDO;

/**
 * 设备回调表(DeviceCallback)表 转换类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分37秒
 */
@Convert
public class DeviceCallbackConvert extends AbstractDoConvert<DeviceCallback, DeviceCallbackDO> {

    @Override
    public DeviceCallback doToEntity(DeviceCallbackDO dO) {
        return new DeviceCallback(dO);
    }
}
