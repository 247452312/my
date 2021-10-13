package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.DeviceDO;
import indi.uhyils.pojo.DTO.DeviceDTO;
import indi.uhyils.pojo.entity.Device;
import org.mapstruct.Mapper;

/**
 * 设备表(Device)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分12秒
 */
@Mapper(componentModel = "spring")
public abstract class DeviceAssembler extends AbstractAssembler<DeviceDO, Device, DeviceDTO> {

}
