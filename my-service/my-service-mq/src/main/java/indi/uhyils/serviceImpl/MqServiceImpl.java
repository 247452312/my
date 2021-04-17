package indi.uhyils.serviceImpl;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.message.MessageFactory;
import indi.uhyils.core.register.RegisterFactory;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.core.topic.TopicFactory;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import indi.uhyils.exception.UserException;
import indi.uhyils.pojo.request.CreateTopicRequest;
import indi.uhyils.pojo.request.RegisterConsumerRequest;
import indi.uhyils.pojo.request.RegisterProviderRequest;
import indi.uhyils.pojo.request.RegisterPublishRequest;
import indi.uhyils.pojo.request.RegisterSubscriberReqeust;
import indi.uhyils.pojo.request.SendMessageRequest;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.annotation.RpcService;
import indi.uhyils.service.MqService;

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
    public ServiceResult<Boolean> registerProvider(RegisterProviderRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PROVIDER, ip, request.getPort(), topic,
            request.getBehavior()));
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerConsumer(RegisterConsumerRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.COMSUMER, ip, request.getPort(), topic,
            request.getBehavior()));
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerPublish(RegisterPublishRequest request, String ip) throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.PUBLISH, ip, request.getPort(), topic,
            request.getBehavior()));
        return ServiceResult.buildSuccessResult(true, request);
    }

    @Override
    public ServiceResult<Boolean> registerSubscriber(RegisterSubscriberReqeust request, String ip)
        throws UserException {
        Topic topic = TopicFactory.getByTopicName(request.getTopicName());
        if (topic == null) {
            return ServiceResult.buildSuccessResult(false, request);
        }
        topic.addNewRegister(RegisterFactory.createOrGetRegister(RegisterType.SUBSCRIBER, ip, request.getPort(), topic,
            request.getBehavior()));
        return ServiceResult.buildSuccessResult(true, request);
    }
}
