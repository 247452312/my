package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.pojo.entity.Device;

/**
 * 设备表(Device)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分23秒
 */
@Assembler
public class DeviceAssembler extends AbstractAssembler<DeviceDO, Device, DeviceDTO> {

    @Override
    public Device toEntity(DeviceDO dO) {
        return new Device(dO);
    }

    @Override
    public Device toEntity(DeviceDTO dto) {
        return new Device(toDo(dto));
    }

    @Override
    protected Class<DeviceDO> getDoClass() {
        return DeviceDO.class;
    }

    @Override
    protected Class<DeviceDTO> getDtoClass() {
        return DeviceDTO.class;
    }
}

