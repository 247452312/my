package indi.uhyils.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.message.MessageFactory;
import indi.uhyils.core.register.RegisterFactory;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.core.topic.TopicFactory;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import indi.uhyils.exception.UserException;
import indi.uhyils.pojo.request.*;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.MqService;
import indi.uhyils.util.LogUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月17日 13时30分
 * @Version 1.0
 */
@RpcService
public class MqServiceImpl implements MqService {
    @Override
    public ServiceResult<Boolean> sendMessage(SendMessageRequest request) throws UserException {
        // 创建topic
        Topic topic = TopicFactory.createOrGetTopic(request);
        Message message = MessageFactory.createMessage(request.getData(), request.getKey(), topic);
        // 保存消息到topic
        return ServiceResult.buildSuccessResult(topic.saveMessage(message), request);
    }

    @Override
    public ServiceResult<JSONObject> getMessage(GetMessageRequest request) {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult("不存在topic", null, request);
        }
        Message message = null;
        try {
            message = topic.getMessage(request.getKey());
        } catch (InterruptedException e) {
            LogUtil.error(this, e);
        }
        if (message == null) {
            return ServiceResult.buildSuccessResult("不存在信息", null, request);
        }
        return ServiceResult.buildSuccessResult(message.getData(), request);
    }

    @Override
    public ServiceResult<Boolean> createTopic(CreateTopicRequest request) throws UserException {
        // 接收默认值
        OutDealTypeEnum receiveType = request.getReceiveType();
        if (receiveType == null) {
            receiveType = OutDealTypeEnum.PASSIVE;
        }
        // 推送默认值
        OutDealTypeEnum pushType = request.getPushType();
        if (pushType == null) {
            pushType = OutDealTypeEnum.PASSIVE;
        }
        TopicFactory.createOrGetTopic(request.getName(), request.getType(), receiveType, pushType, request.getKey());
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerProvider(RegisterProviderRequest request) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        if (StringUtils.isBlank(request.getChannelId())) {
            topic.addNewRegister(RegisterFactory.createOrGetUrlRegister(RegisterType.PROVIDER, request.getUrl(), topic, request.getBehavior()));
        } else {
            topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PROVIDER, request.getChannelId(), topic, request.getBehavior()));
        }
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerConsumer(RegisterConsumerRequest request) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        if (StringUtils.isBlank(request.getChannelId())) {
            topic.addNewRegister(RegisterFactory.createOrGetUrlRegister(RegisterType.COMSUMER, request.getUrl(), topic, request.getBehavior()));
        } else {
            topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.COMSUMER, request.getChannelId(), topic, request.getBehavior()));
        }
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerPublish(RegisterPublishRequest request) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        if (StringUtils.isBlank(request.getChannelId())) {
            topic.addNewRegister(RegisterFactory.createOrGetUrlRegister(RegisterType.PUBLISH, request.getUrl(), topic, request.getBehavior()));
        } else {
            topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PUBLISH, request.getChannelId(), topic, request.getBehavior()));
        }
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerSubscriber(RegisterSubscriberReqeust request)
            throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        if (StringUtils.isBlank(request.getChannelId())) {
            topic.addNewRegister(RegisterFactory.createOrGetUrlRegister(RegisterType.SUBSCRIBER, request.getUrl(), topic, request.getBehavior()));
        } else {
            topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.SUBSCRIBER, request.getChannelId(), topic, request.getBehavior()));
        }
        return ServiceResult.buildSuccessResult(true, request);
    }
}
