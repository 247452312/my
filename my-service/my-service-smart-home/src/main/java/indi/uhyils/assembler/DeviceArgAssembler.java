package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.pojo.DTO.DeviceArgDTO;
import indi.uhyils.pojo.entity.DeviceArg;

/**
 * 设备参数表(DeviceArg)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月27日 08时21分11秒
 */
@Assembler
public class DeviceArgAssembler extends AbstractAssembler<DeviceArgDO, DeviceArg, DeviceArgDTO> {

    @Override
    public DeviceArg toEntity(DeviceArgDO dO) {
        return new DeviceArg(dO);
    }

    @Override
    public DeviceArg toEntity(DeviceArgDTO dto) {
        return new DeviceArg(toDo(dto));
    }

    @Override
    protected Class<DeviceArgDO> getDoClass() {
        return DeviceArgDO.class;
    }

    @Override
    protected Class<DeviceArgDTO> getDtoClass() {
        return DeviceArgDTO.class;
    }
}

