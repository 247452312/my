package indi.uhyils.protocol.rpc.provider;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.exception.UserException;
import indi.uhyils.pojo.DTO.request.CreateTopicRequest;
import indi.uhyils.pojo.DTO.request.GetMessageRequest;
import indi.uhyils.pojo.DTO.request.RegisterConsumerRequest;
import indi.uhyils.pojo.DTO.request.RegisterProviderRequest;
import indi.uhyils.pojo.DTO.request.RegisterPublishRequest;
import indi.uhyils.pojo.DTO.request.RegisterSubscriberReqeust;
import indi.uhyils.pojo.DTO.request.SendMessageRequest;
import indi.uhyils.pojo.DTO.base.ServiceResult;
import indi.uhyils.protocol.rpc.base.BaseProvider;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月17日 13时15分
 * @Version 1.0
 */
public interface MqProvider extends BaseProvider {

    /**
     * 发送消息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> sendMessage(SendMessageRequest request) throws UserException;

    /**
     * 获取消息
     *
     * @param request
     *
     * @return
     */
    ServiceResult<JSONObject> getMessage(GetMessageRequest request) throws UserException;

    /**
     * 创建主题
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> createTopic(CreateTopicRequest request) throws UserException;

    /**
     * 注册一个生产者
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> registerProvider(RegisterProviderRequest request) throws UserException;

    /**
     * 注册一个消费者
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> registerConsumer(RegisterConsumerRequest request) throws UserException;

    /**
     * 注册一个发布者
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> registerPublish(RegisterPublishRequest request) throws UserException;

    /**
     * 注册一个订阅者
     *
     * @param request
     *
     * @return
     */
    ServiceResult<Boolean> registerSubscriber(RegisterSubscriberReqeust request) throws UserException;
}
