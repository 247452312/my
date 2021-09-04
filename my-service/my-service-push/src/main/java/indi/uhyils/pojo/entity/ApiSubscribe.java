package indi.uhyils.pojo.entity;

import indi.uhyils.enum_.PushTypeEnum;
import indi.uhyils.pojo.DO.ApiSubscribeDO;
import indi.uhyils.pojo.DTO.PushMsgDTO;
import indi.uhyils.pojo.DTO.UserDTO;
import indi.uhyils.pojo.entity.type.Identifier;
import indi.uhyils.repository.ApiSubscribeRepository;
import indi.uhyils.util.AssertUtil;
import indi.uhyils.util.CollectionUtil;
import indi.uhyils.util.PushUtils;
import java.util.List;
import java.util.Objects;

/**
 * api订阅表(ApiSubscribe)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年09月02日 19时46分53秒
 */
public class ApiSubscribe extends AbstractDoEntity<ApiSubscribeDO> {

    public ApiSubscribe(ApiSubscribeDO dO) {
        super(dO);
    }

    public ApiSubscribe() {
        super(new ApiSubscribeDO());
    }

    public ApiSubscribe(Long id) {
        super(id, new ApiSubscribeDO());
    }

    public ApiSubscribe(Long id, ApiSubscribeRepository rep) {
        super(id, new ApiSubscribeDO());
        completion(rep);
    }

    public void checkRepeat(ApiSubscribeRepository rep) {
        List<ApiSubscribe> subscribes = rep.findByGroupAndUserId(new Identifier(toDo().getApiGroupId()), new Identifier(toDo().getUserId()));
        AssertUtil.assertTrue(CollectionUtil.isEmpty(subscribes), "不能同时订阅同一个api两次");
    }

    public PushMsgDTO sendMsg(ApiGroup apiGroup, UserDTO userDTO, String sendContent) {
        switch (Objects.requireNonNull(PushTypeEnum.prase(toDo().getType()))) {
            case PAGE:
                return PushUtils.pagePush(userDTO, "my系统,订阅消息-" + apiGroup.data.getName(), sendContent);
            case EMAIL:
                return PushUtils.emailPush(userDTO, "my系统,订阅邮件-" + apiGroup.data.getName(), sendContent);
            default:
                break;
        }
        return null;
    }

    public PushMsgDTO sendMsg(UserDTO userDTO, String title, String sendContent, PushTypeEnum pushType) {
        switch (pushType) {
            case PAGE:
                return PushUtils.pagePush(userDTO, title, sendContent);
            case EMAIL:
                return PushUtils.emailPush(userDTO, title, sendContent);
            default:
                break;
        }
        return null;
    }

}
