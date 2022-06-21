package indi.uhyils.service.impl;

import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.assembler.PushMsgAssembler;
import indi.uhyils.enums.PushTypeEnum;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DO.PushMsgDO;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.DTO.request.CronRequest;
import indi.uhyils.pojo.DTO.request.PushMsgToSomeoneRequest;
import indi.uhyils.pojo.entity.AllApiGroup;
import indi.uhyils.pojo.entity.ApiSubscribe;
import indi.uhyils.pojo.entity.PushMsg;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiGroupRepository;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.repository.PushMsgRepository;
import indi.uhyils.service.PushMsgService;
import indi.uhyils.util.Asserts;
import indi.uhyils.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 推送日志表(PushMsg)表 内部服务实现类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月02日 19时47分05秒
 */
@Service
@ReadWriteMark(tables = {"sys_push_msg"})
public class PushMsgServiceImpl extends AbstractDoService<PushMsgDO, PushMsg, PushMsgDTO, PushMsgRepository, PushMsgAssembler> implements PushMsgService {

    @Autowired
    private ApiGroupRepository apiGroupRepository;

    @Autowired
    private ApiRepository apiRepository;

    @Autowired
    private ApiSubscribeRepository subscribeRepository;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private PushMsgRepository msgRepository;

    @Autowired
    private PushMsgAssembler msgAssembler;

    public PushMsgServiceImpl(PushMsgAssembler assembler, PushMsgRepository repository) {
        super(assembler, repository);
    }

    @Override
    public Boolean push(CronRequest request) {
        AllApiGroup apiGroups = new AllApiGroup(apiGroupRepository);
        apiGroups.fillApi(apiRepository);
        apiGroups.fillSubscribe(subscribeRepository, request.getCron());
        apiGroups.sendMsgToUser(userFacade, msgRepository, msgAssembler);
        LogUtil.info(this, "定时推送任务结束: " + request.getCron());
        return true;
    }

    @Override
    public Boolean pushMsgToSomeone(PushMsgToSomeoneRequest request) {
        PushTypeEnum type = PushTypeEnum.prase(request.getType());
        Asserts.assertTrue(type != null, "类型不正确");
        UserDTO user = userFacade.getById(new Identifier(request.getUserId()));
        ApiSubscribe apiSubscribe = new ApiSubscribe();
        PushMsgDTO pushMsgDTO = apiSubscribe.sendMsg(user, request.getTitle(), request.getMsg(), type);
        PushMsg pushMsg = msgAssembler.toEntity(pushMsgDTO);
        msgRepository.save(pushMsg);
        return true;
    }
}
