package indi.uhyils.pojo.entity;

import indi.uhyils.pojo.DO.DeviceArgDO;

/**
 * 设备参数表(DeviceArg)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年08月31日 19时04分15秒
 */
public class DeviceArg extends AbstractDoEntity<DeviceArgDO> {

    public DeviceArg(DeviceArgDO dO) {
        super(dO);
    }

    public DeviceArg(Long id) {
        super(id, new DeviceArgDO());
    }

}
