package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.entity.base.AbstractDoEntity;

/**
 * 设备表(Device)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分11秒
 */
public class Device extends AbstractDoEntity<DeviceDO> {

    public Device(DeviceDO dO) {
        super(dO);
    }

    public Device(Long id) {
        super(id, new DeviceDO());
    }

}
