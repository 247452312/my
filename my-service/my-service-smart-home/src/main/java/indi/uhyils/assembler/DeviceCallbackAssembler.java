package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.DTO.DeviceCallbackDTO;
import indi.uhyils.pojo.entity.DeviceCallback;

/**
 * 设备回调表(DeviceCallback)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月26日 22时50分34秒
 */
@Assembler
public class DeviceCallbackAssembler extends AbstractAssembler<DeviceCallbackDO, DeviceCallback, DeviceCallbackDTO> {

    @Override
    public DeviceCallback toEntity(DeviceCallbackDO dO) {
        return new DeviceCallback(dO);
    }

    @Override
    public DeviceCallback toEntity(DeviceCallbackDTO dto) {
        return new DeviceCallback(toDo(dto));
    }

    @Override
    protected Class<DeviceCallbackDO> getDoClass() {
        return DeviceCallbackDO.class;
    }

    @Override
    protected Class<DeviceCallbackDTO> getDtoClass() {
        return DeviceCallbackDTO.class;
    }
}

