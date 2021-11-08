package indi.uhyils.assembler;


import org.mapstruct.Mapper;
import indi.uhyils.pojo.DO.MqInfoDO;
import indi.uhyils.pojo.DTO.MqInfoDTO;
import indi.uhyils.pojo.entity.MqInfo;

/**
 * mq连接信息表(MqInfo)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@Mapper(componentModel = "spring")
public abstract class MqInfoAssembler extends AbstractAssembler<MqInfoDO, MqInfo, MqInfoDTO> {

}

