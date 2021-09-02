package indi.uhyils.assembler;


import indi.uhyils.annotation.Assembler;
import indi.uhyils.pojo.DO.PushMsgDO;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.entity.PushMsg;

/**
 * 推送日志表(PushMsg)表 entity,DO,DTO转换工具
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分03秒
 */
@Assembler
public class PushMsgAssembler extends AbstractAssembler<PushMsgDO, PushMsg, PushMsgDTO> {

    @Override
    public PushMsg toEntity(PushMsgDO dO) {
        return new PushMsg(dO);
    }

    @Override
    public PushMsg toEntity(PushMsgDTO dto) {
        return new PushMsg(toDo(dto));
    }

    @Override
    protected Class<PushMsgDO> getDoClass() {
        return PushMsgDO.class;
    }

    @Override
    protected Class<PushMsgDTO> getDtoClass() {
        return PushMsgDTO.class;
    }
}

