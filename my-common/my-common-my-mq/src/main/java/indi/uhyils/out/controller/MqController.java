package indi.uhyils.out.controller;

import indi.uhyils.core.exception.UserException;
import indi.uhyils.core.service.MqService;
import indi.uhyils.pojo.request.*;
import indi.uhyils.util.IpUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 13时27分
 * @Version 1.0
 */
@RestController("/MQ")
public class MqController {

    @Resource
    private MqService service;

    /**
     * 发送消息
     *
     * @param request
     * @return
     * @throws UserException
     */
    @PostMapping("sendMessage")
    public Boolean sendMessage(@RequestBody SendMessageRequest request) throws UserException {
        return service.sendMessage(request);
    }

    /**
     * 创建一个topic
     *
     * @param request
     * @return
     * @throws UserException
     */
    @PostMapping("createTopic")
    public Boolean createTopic(@RequestBody CreateTopicRequest request) throws UserException {
        return service.createTopic(request);
    }

    /**
     * 注册一个消息生产者
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping("registerProvider")
    public Boolean registerProvider(@RequestBody RegisterProviderRequest request, HttpServletRequest httpServletRequest) {
        return service.registerProvider(request, IpUtil.getIP(httpServletRequest));
    }

    /**
     * 注册一个消息消费者
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping("registerConsumer")
    public Boolean registerConsumer(@RequestBody RegisterConsumerRequest request, HttpServletRequest httpServletRequest) {
        return service.registerConsumer(request, IpUtil.getIP(httpServletRequest));
    }

    /**
     * 注册一个消息发布者
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping("registerPublish")
    public Boolean registerPublish(@RequestBody RegisterPublishRequest request, HttpServletRequest httpServletRequest) {
        return service.registerPublish(request, IpUtil.getIP(httpServletRequest));
    }

    /**
     * 注册一个消息订阅者
     *
     * @param request
     * @param httpServletRequest
     * @return
     */
    @PostMapping("registerSubscriber")
    public Boolean registerSubscriber(@RequestBody RegisterSubscriberReqeust request, HttpServletRequest httpServletRequest) {
        return service.registerSubscriber(request, IpUtil.getIP(httpServletRequest));
    }

}
