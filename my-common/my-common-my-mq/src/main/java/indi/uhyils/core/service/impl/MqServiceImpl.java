package indi.uhyils.core.service.impl;

import indi.uhyils.core.exception.UserException;
import indi.uhyils.core.message.Message;
import indi.uhyils.core.message.MessageFactory;
import indi.uhyils.core.register.Register;
import indi.uhyils.core.register.RegisterFactory;
import indi.uhyils.core.register.RegisterType;
import indi.uhyils.core.service.MqService;
import indi.uhyils.core.topic.OutDealTypeEnum;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.core.topic.TopicFactory;
import indi.uhyils.core.topic.TopicType;
import indi.uhyils.pojo.request.*;
import org.springframework.stereotype.Service;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时59分
 * @Version 1.0
 */
@Service
public class MqServiceImpl implements MqService {
    @Override
    public Boolean sendMessage(SendMessageRequest request) throws UserException {
        // 创建topic
        Topic topic = TopicFactory.createOrGetTopic(request);
        Message message = MessageFactory.createMessage(request.getData(), request.getKey(), topic);
        // 保存消息到topic
        return topic.saveMessage(message);
    }

    @Override
    public Boolean createTopic(CreateTopicRequest request) throws UserException {
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
        return true;
    }

    @Override
    public Boolean registerProvider(RegisterProviderRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return false;
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PROVIDER, ip, request.getPort(), topic,
            request.getBehavior()));
        return true;
    }

    @Override
    public Boolean registerConsumer(RegisterConsumerRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return false;
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.COMSUMER, ip, request.getPort(), topic,
            request.getBehavior()));
        return true;
    }

    @Override
    public Boolean registerPublish(RegisterPublishRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return false;
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PUBLISH, ip, request.getPort(), topic,
            request.getBehavior()));
        return true;
    }

    @Override
    public Boolean registerSubscriber(RegisterSubscriberReqeust request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return false;
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.SUBSCRIBER, ip, request.getPort(), topic,
            request.getBehavior()));
        return true;
    }
}
