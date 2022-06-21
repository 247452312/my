package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.DeviceArgDO;
import indi.uhyils.pojo.DTO.DeviceArgDTO;
import indi.uhyils.pojo.entity.DeviceArg;
import org.mapstruct.Mapper;

/**
 * 设备参数表(DeviceArg)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月31日 19时04分16秒
 */
@Mapper(componentModel = "spring")
public abstract class DeviceArgAssembler extends AbstractAssembler<DeviceArgDO, DeviceArg, DeviceArgDTO> {

}
