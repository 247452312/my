package indi.uhyils.core.register;

import indi.uhyils.core.topic.Topic;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.RegisterType;
import indi.uhyils.exception.ExpressionInvalidException;
import indi.uhyils.exception.NotFoundRegisterTypeException;
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
     * @param port
     * @param topic
     * @return
     * @throws UserException
     */
    public static Register createOrGetRegister(RegisterType type, String ip, Integer port, Topic topic,
                                               OutDealTypeEnum outDealTypeEnum) throws UserException {
        Register oneRegister = findOneRegister(type, ip, port, topic.getName());
        if (oneRegister != null) {
            return oneRegister;
        }
        switch (type) {
            case PROVIDER:
                oneRegister = createNewProvider(ip, port, topic, outDealTypeEnum);
                break;
            case COMSUMER:
                oneRegister = createNewConsumer(ip, port, topic, outDealTypeEnum);
                break;
            case PUBLISH:
                oneRegister = createNewPublish(ip, port, topic, outDealTypeEnum);
                break;
            case SUBSCRIBER:
                oneRegister = createNewSubscriber(ip, port, topic, outDealTypeEnum);
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
     * @param ip
     * @param port
     * @param topic
     * @return
     */
    private static Register createNewProvider(String ip, Integer port, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException {
        Provider provider = new Provider(ip, port, outDealTypeEnum);
        provider.setTopic(topic);
        topic.addNewRegister(provider);
        return provider;
    }

    /**
     * 创建一个新的消息消费者
     *
     * @param ip
     * @param port
     * @param topic
     * @return
     */
    private static Register createNewConsumer(String ip, Integer port, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException {
        Consumer consumer = new Consumer(ip, port, outDealTypeEnum);
        consumer.setTopic(topic);
        topic.addNewRegister(consumer);
        return consumer;
    }

    /**
     * 创建一个新的消息发布者
     *
     * @param ip
     * @param port
     * @param topic
     * @return
     */
    private static Register createNewPublish(String ip, Integer port, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException {
        Publish publish = new Publish(ip, port, outDealTypeEnum);
        publish.setTopic(topic);
        topic.addNewRegister(publish);
        return publish;
    }

    /**
     * 创建一个新的消息订阅者
     *
     * @param ip
     * @param port
     * @param topic
     * @return
     */
    private static Register createNewSubscriber(String ip, Integer port, Topic topic, OutDealTypeEnum outDealTypeEnum) throws ExpressionInvalidException {
        Subscriber subscriber = new Subscriber(ip, port, outDealTypeEnum);
        subscriber.setTopic(topic);
        topic.addNewRegister(subscriber);
        return subscriber;
    }

    /**
     * 通过指定参数获取某些注册者
     *
     * @param type
     * @param ip
     * @param port
     * @param topicName
     * @return
     */
    public static Collection<Register> findRegister(RegisterType type, String ip, Integer port, String topicName) {
        Collection<Register> basic = new ArrayList<>();
        // 过滤类型
        if (type == null) {
            REGISTER_TYPE_COLLECTION_MAP.values().forEach(basic::addAll);
        } else {
            basic.addAll(REGISTER_TYPE_COLLECTION_MAP.get(type));
        }
        Stream<Register> basicStream = basic.stream();
        // 过滤ip
        if (ip != null) {
            basicStream = basicStream.filter(t -> ip.equals(t.getIp()));
        }
        // 过滤端口
        if (port != null) {
            basicStream = basicStream.filter(t -> port.equals(t.getPort()));
        }
        // 过滤
        if (topicName != null) {
            basicStream = basicStream.filter(t -> topicName.equals(t.getTopicName()));
        }
        return basicStream.collect(Collectors.toSet());
    }

    /**
     * 获取一个符合条件的注册者(不是随机)
     *
     * @param type
     * @param ip
     * @param port
     * @param topicName
     * @return
     * @throws UserException
     */
    public static Register findOneRegister(RegisterType type, String ip, Integer port, String topicName) {
        Collection<Register> register = findRegister(type, ip, port, topicName);
        if (register.size() >= 1) {
            return register.iterator().next();
        } else {
            return null;
        }
    }

}
