package indi.uhyils.pojo.entity;

import indi.uhyils.assembler.PushMsgAssembler;
import indi.uhyils.facade.UserFacade;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.repository.ApiGroupRepository;
import indi.uhyils.repository.ApiRepository;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.repository.PushMsgRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年09月03日 08时32分
 */
public class AllApiGroup extends AbstractEntity {

    private final List<ApiGroup> groups;


    public AllApiGroup(ApiGroupRepository repository) {
        this.groups = repository.findAll();
    }

    public void fillApi(ApiRepository apiRepository) {
        List<Api> apis = apiRepository.findAll();
        Map<Long, List<Api>> groupIdApiMap = apis.stream().collect(Collectors.groupingBy(t -> t.toDo().getApiGroupId()));
        for (ApiGroup group : groups) {
            if (!groupIdApiMap.containsKey(group.id.getId())) {
                continue;
            }
            List<Api> groupApis = groupIdApiMap.get(group.id.getId());
            group.forceFillApi(groupApis);
        }
    }

    public void fillSubscribe(ApiSubscribeRepository subscribeRepository, String cron) {
        List<ApiSubscribe> subscribes = subscribeRepository.findByCron(cron);
        Map<Long, List<ApiSubscribe>> groupIdSubscribeMap = subscribes.stream().collect(Collectors.groupingBy(t -> t.toDo().getApiGroupId()));
        for (ApiGroup group : groups) {
            if (!groupIdSubscribeMap.containsKey(group.id.getId())) {
                continue;
            }
            List<ApiSubscribe> groupSubscribes = groupIdSubscribeMap.get(group.id.getId());
            group.forceFillSubscribe(groupSubscribes);
        }
    }

    public List<PushMsgDTO> sendMsgToUser(UserFacade userFacade) {
        List<PushMsgDTO> result = new ArrayList<>();
        for (ApiGroup group : groups) {
            List<PushMsgDTO> pushMsgDTOS = group.sendMsgToUser(userFacade);
            result.addAll(pushMsgDTOS);
        }
        return result;
    }

    public void sendMsgToUser(UserFacade userFacade, PushMsgRepository msgRepository, PushMsgAssembler msgAssembler) {
        List<PushMsgDTO> pushMsgDTOS = sendMsgToUser(userFacade);
        List<PushMsg> pushMsgs = msgAssembler.listDTOToEntity(pushMsgDTOS);
        msgRepository.save(pushMsgs);
    }
}
