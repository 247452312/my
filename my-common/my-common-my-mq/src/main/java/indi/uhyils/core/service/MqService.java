package indi.uhyils.core.service;

import indi.uhyils.core.exception.UserException;
import indi.uhyils.pojo.request.*;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时59分
 * @Version 1.0
 */
public interface MqService {
    /**
     * 发送消息
     *
     * @param request
     * @return
     */
    Boolean sendMessage(SendMessageRequest request) throws UserException;

    /**
     * 创建主题
     *
     * @param request
     * @return
     */
    Boolean createTopic(CreateTopicRequest request) throws UserException;

    /**
     * 注册一个生产者
     *
     * @param request
     * @param ip
     * @return
     */
    Boolean registerProvider(RegisterProviderRequest request, String ip) throws UserException;

    /**
     * 注册一个消费者
     *
     * @param request
     * @param ip
     * @return
     */
    Boolean registerConsumer(RegisterConsumerRequest request, String ip) throws UserException;

    /**
     * 注册一个发布者
     *
     * @param request
     * @param ip
     * @return
     */
    Boolean registerPublish(RegisterPublishRequest request, String ip) throws UserException;

    /**
     * 注册一个订阅者
     *
     * @param request
     * @param ip
     * @return
     */
    Boolean registerSubscriber(RegisterSubscriberReqeust request, String ip) throws UserException;
}
