package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 设备回调表(DeviceCallback)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分20秒
 */
public class DeviceCallback extends AbstractDoEntity<DeviceCallbackDO> {

    public DeviceCallback(DeviceCallbackDO dO) {
        super(dO);
    }

    public DeviceCallback(Long id) {
        super(id, new DeviceCallbackDO());
    }

}
