package indi.uhyils.assembler;


import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.DTO.PushPageMsgDTO;
import indi.uhyils.pojo.entity.PushPageMsg;
import org.mapstruct.Mapper;

/**
 * 推送日志信息表(PushPageMsg)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分08秒
 */
@Mapper(componentModel = "spring")
public abstract class PushPageMsgAssembler extends AbstractAssembler<PushPageMsgDO, PushPageMsg, PushPageMsgDTO> {

}
