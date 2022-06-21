package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.DeviceCallbackDO;
import indi.uhyils.pojo.DTO.DeviceCallbackDTO;
import indi.uhyils.pojo.entity.DeviceCallback;
import org.mapstruct.Mapper;

/**
 * 设备回调表(DeviceCallback)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分20秒
 */
@Mapper(componentModel = "spring")
public abstract class DeviceCallbackAssembler extends AbstractAssembler<DeviceCallbackDO, DeviceCallback, DeviceCallbackDTO> {

}
