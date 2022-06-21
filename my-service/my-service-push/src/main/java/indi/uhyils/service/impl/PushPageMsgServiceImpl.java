package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PushPageMsgAssembler;
import indi.uhyils.pojo.DO.PushPageMsgDO;
import indi.uhyils.pojo.DTO.PushPageMsgDTO;
import indi.uhyils.pojo.entity.PushPageMsg;
import indi.uhyils.repository.PushPageMsgRepository;
import indi.uhyils.service.PushPageMsgService;
import org.springframework.stereotype.Service;

/**
 * 推送日志信息表(PushPageMsg)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分09秒
 */
@Service
@ReadWriteMark(tables = {"sys_push_page_msg"})
public class PushPageMsgServiceImpl extends AbstractDoService<PushPageMsgDO, PushPageMsg, PushPageMsgDTO, PushPageMsgRepository, PushPageMsgAssembler> implements PushPageMsgService {

    public PushPageMsgServiceImpl(PushPageMsgAssembler assembler, PushPageMsgRepository repository) {
        super(assembler, repository);
    }


}
