package indi.uhyils.core.register;

import indi.uhyils.core.topic.Topic;

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

    public AbstractRegister(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
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

    /**
     * 获取注册者的类型
     *
     * @return
     */
    public abstract RegisterType getRegisterType();
}
