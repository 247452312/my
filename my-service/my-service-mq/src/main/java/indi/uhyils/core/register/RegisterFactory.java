package indi.uhyils.core.register;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import indi.uhyils.exception.ExpressionInvalidException;
import indi.uhyils.exception.NotFoundRegisterTypeException;
import indi.uhyils.exception.RegisterTopicNotFoundException;
import indi.uhyils.exception.UserException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 注册者管理者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 22时02分
 * @Version 1.0
 */
public class RegisterFactory {

    /**
     * 注册的所有者
     */
    private static final Map<RegisterType, Collection<Register>> REGISTER_TYPE_COLLECTION_MAP = new HashMap<>();

    static {
        // 初始化
        for (RegisterType value : RegisterType.values()) {
            REGISTER_TYPE_COLLECTION_MAP.put(value, new ArrayList<>());
        }
    }

    /**
     * 获取一个注册者,没有则创建
     *
     * @param type
     * @param ip
     * @param topic
     *
     * @return
     *
     * @throws UserException
     */
    public static Register createOrGetUrlRegister(
        RegisterType type, String ip, Topic topic,
        OutDealTypeEnum outDealTypeEnum) throws UserException {
        Register oneRegister = findOneUrlRegister(type, ip, topic.getName());
        if (oneRegister != null) {
            return oneRegister;
        }
        switch (type) {
            case PROVIDER:
                oneRegister = createNewUrlProvider(ip, topic, outDealTypeEnum);
                break;
            case COMSUMER:
                oneRegister = createNewUrlConsumer(ip, topic, outDealTypeEnum);
                break;
            case PUBLISH:
                oneRegister = createNewUrlPublish(ip, topic, outDealTypeEnum);
                break;
            case SUBSCRIBER:
                oneRegister = createNewUrlSubscriber(ip, topic, outDealTypeEnum);
                break;
            default:
                throw new NotFoundRegisterTypeException();
        }
        REGISTER_TYPE_COLLECTION_MAP.get(type).add(oneRegister);
        return oneRegister;
    }

    /**
     * 获取一个注册者,没有则创建
     *
     * @param type
     * @param topic
     *
     * @return
     *
     * @throws UserException
     */
    public static Register createOrGetRegister(
        RegisterType type, String channelId, Topic topic,
        OutDealTypeEnum outDealTypeEnum) throws UserException {
        Register oneRegister = findOneRegister(type, channelId, topic.getName());
        if (oneRegister != null) {
            return oneRegister;
        }
        switch (type) {
            case PROVIDER:
                oneRegister = createNewUrlProvider(channelId, topic, outDealTypeEnum);
                break;
            case COMSUMER:
                oneRegister = createNewConsumer(channelId, topic, outDealTypeEnum);
                break;
            case PUBLISH:
                oneRegister = createNewPublish(channelId, topic, outDealTypeEnum);
                break;
            case SUBSCRIBER:
                oneRegister = createNewSubscriber(channelId, topic, outDealTypeEnum);
                break;
            default:
                throw new NotFoundRegisterTypeException();
        }
        REGISTER_TYPE_COLLECTION_MAP.get(type).add(oneRegister);
        return oneRegister;
    }

    /**
     * 创建一个新的消息提供者
     *
     * @param url
     * @param topic
     *
     * @return
     */
    private static Register createNewUrlProvider(String url, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Provider provider = Provider.buildUrlRegister(url, outDealTypeEnum);
        provider.setTopic(topic);
        if (!topic.addNewRegister(provider)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), provider.getBehaviorType(), provider.getBehaviorType());
        }
        return provider;
    }

    /**
     * 创建一个新的消息提供者
     *
     * @param topic
     *
     * @return
     */
    private static Register createNewProvider(String channelId, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Provider provider = Provider.buildChannelRegister(channelId, outDealTypeEnum);
        provider.setTopic(topic);
        if (!topic.addNewRegister(provider)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), provider.getBehaviorType(), provider.getBehaviorType());
        }
        return provider;
    }

    /**
     * 创建一个新的消息消费者
     *
     * @param url
     * @param topic
     *
     * @return
     */
    private static Register createNewUrlConsumer(String url, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Consumer consumer = Consumer.buildUrlRegister(url, outDealTypeEnum);
        consumer.setTopic(topic);
        if (!topic.addNewRegister(consumer)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), consumer.getBehaviorType(), consumer.getBehaviorType());
        }
        return consumer;
    }

    /**
     * 创建一个新的消息消费者
     *
     * @param topic
     *
     * @return
     */
    private static Register createNewConsumer(String channelId, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Consumer consumer = Consumer.buildChannelRegister(channelId, outDealTypeEnum);
        consumer.setTopic(topic);
        if (!topic.addNewRegister(consumer)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), consumer.getBehaviorType(), consumer.getBehaviorType());
        }
        return consumer;
    }

    /**
     * 创建一个新的消息发布者
     *
     * @param url
     * @param topic
     *
     * @return
     */
    private static Register createNewUrlPublish(String url, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Publish publish = Publish.buildUrlRegister(url, outDealTypeEnum);
        publish.setTopic(topic);
        if (!topic.addNewRegister(publish)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), publish.getBehaviorType(), publish.getBehaviorType());
        }
        return publish;
    }

    /**
     * 创建一个新的消息发布者
     *
     * @param topic
     *
     * @return
     */
    private static Register createNewPublish(String channelId, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Publish publish = Publish.buildChannelRegister(channelId, outDealTypeEnum);
        publish.setTopic(topic);
        if (!topic.addNewRegister(publish)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), publish.getBehaviorType(), publish.getBehaviorType());
        }
        return publish;
    }

    /**
     * 创建一个新的消息订阅者
     *
     * @param url
     * @param topic
     *
     * @return
     */
    private static Register createNewUrlSubscriber(String url, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Subscriber subscriber = Subscriber.buildUrlRegister(url, outDealTypeEnum);
        subscriber.setTopic(topic);
        if (!topic.addNewRegister(subscriber)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), subscriber.getBehaviorType(), subscriber.getBehaviorType());
        }
        return subscriber;
    }

    /**
     * 创建一个新的消息订阅者
     *
     * @param topic
     *
     * @return
     */
    private static Register createNewSubscriber(String channelId, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException, RegisterTopicNotFoundException {
        Subscriber subscriber = Subscriber.buildChannelRegister(channelId, outDealTypeEnum);
        subscriber.setTopic(topic);
        if (!topic.addNewRegister(subscriber)) {
            throw new RegisterTopicNotFoundException(topic.getName(), topic.getReceiveType(), topic.getPushType(), subscriber.getBehaviorType(), subscriber.getBehaviorType());
        }
        return subscriber;
    }

    /**
     * 通过指定参数获取某些注册者
     *
     * @param type
     * @param url
     * @param topicName
     *
     * @return
     */
    public static Collection<Register> findUrlRegister(RegisterType type, String url, String topicName) {
        Collection<Register> basic = new ArrayList<>();
        // 过滤类型
        if (type == null) {
            REGISTER_TYPE_COLLECTION_MAP.values().forEach(basic::addAll);
        } else {
            basic.addAll(REGISTER_TYPE_COLLECTION_MAP.get(type));
        }
        Stream<Register> basicStream = basic.stream();
        // 过滤url
        if (url != null) {
            basicStream = basicStream.filter(t -> url.equals(t.getUrl()));
        }
        // 过滤topic
        if (topicName != null) {
            basicStream = basicStream.filter(t -> topicName.equals(t.getTopicName()));
        }
        return basicStream.collect(Collectors.toSet());
    }

    /**
     * 通过指定参数获取某些注册者
     *
     * @param type
     * @param topicName
     *
     * @return
     */
    public static Collection<Register> findRegister(RegisterType type, String channelId, String topicName) {
        Collection<Register> basic = new ArrayList<>();
        // 过滤类型
        if (type == null) {
            REGISTER_TYPE_COLLECTION_MAP.values().forEach(basic::addAll);
        } else {
            basic.addAll(REGISTER_TYPE_COLLECTION_MAP.get(type));
        }
        Stream<Register> basicStream = basic.stream();
        // 过滤netty channelId
        if (channelId != null) {
            basicStream = basicStream.filter(t -> channelId.equals(t.getChannelId()));
        }
        // 过滤topic
        if (topicName != null) {
            basicStream = basicStream.filter(t -> topicName.equals(t.getTopicName()));
        }
        return basicStream.collect(Collectors.toSet());
    }

    /**
     * 获取一个符合条件的注册者(不是随机)
     *
     * @param type
     * @param url
     * @param topicName
     *
     * @return
     *
     * @throws UserException
     */
    public static Register findOneUrlRegister(RegisterType type, String url, String topicName) {
        Collection<Register> register = findUrlRegister(type, url, topicName);
        if (register.size() >= 1) {
            return register.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * 获取一个符合条件的注册者(不是随机)
     *
     * @param type
     * @param topicName
     *
     * @return
     *
     * @throws UserException
     */
    public static Register findOneRegister(RegisterType type, String channelId, String topicName) {
        Collection<Register> register = findRegister(type, channelId, topicName);
        if (register.size() >= 1) {
            return register.iterator().next();
        } else {
            return null;
        }
    }

}
