package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.DTO.PushPageMsgDTO;
import indi.uhyils.pojo.entity.PushPageMsg;

/**
 * 推送日志信息表(PushPageMsg)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分08秒
 */
@Assembler
public class PushPageMsgAssembler extends AbstractAssembler<PushPageMsgDO, PushPageMsg, PushPageMsgDTO> {

    @Override
    public PushPageMsg toEntity(PushPageMsgDO dO) {
        return new PushPageMsg(dO);
    }

    @Override
    public PushPageMsg toEntity(PushPageMsgDTO dto) {
        return new PushPageMsg(toDo(dto));
    }

    @Override
    protected Class<PushPageMsgDO> getDoClass() {
        return PushPageMsgDO.class;
    }

    @Override
    protected Class<PushPageMsgDTO> getDtoClass() {
        return PushPageMsgDTO.class;
    }
}

