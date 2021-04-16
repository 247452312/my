package indi.uhyils.core.register;

import indi.uhyils.core.message.Message;
import indi.uhyils.core.topic.OutDealTypeEnum;
import indi.uhyils.core.topic.Topic;
import indi.uhyils.util.PushUtil;

/**
 * 注册者模板
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月16日 08时58分
 */
public abstract class AbstractRegister implements Register {

    /**
     * ip
     */
    protected final String ip;

    /**
     * port
     */
    protected final Integer port;

    /**
     * 注册者所在的topic
     */
    protected Topic topic;

    /**
     * 行为类型
     */
    protected OutDealTypeEnum outDealTypeEnum;

    public AbstractRegister(String ip, Integer port,OutDealTypeEnum outDealTypeEnum) {
        this.ip = ip;
        this.port = port;
        this.outDealTypeEnum = outDealTypeEnum;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public String getTopicName() {
        if (topic == null) {
            return "";
        }
        return topic.getName();
    }

    @Override
    public OutDealTypeEnum getBehaviorType() {
        return outDealTypeEnum;
    }

    @Override
    public Boolean pushMessage(Message message) {
        return PushUtil.push(this, message);
    }
}
