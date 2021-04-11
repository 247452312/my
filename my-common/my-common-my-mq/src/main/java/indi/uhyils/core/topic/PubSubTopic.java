package indi.uhyils.core.topic;

import indi.uhyils.core.message.Message;


/**
 * 发布订阅主题
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时47分
 * @Version 1.0
 */
public class PubSubTopic extends AbstractTopic {

    public PubSubTopic(String name) {
        super(name);
    }

    public static PubSubTopic build(String name, PushType pushType, ReceiveType receiveType) {
        PubSubTopic build = new PubSubTopic(name);
        build.setPushType(pushType);
        build.setReceiveType(receiveType);
        return build;
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.PUB_SUB;
    }

    @Override
    protected Boolean saveMessage0(Message message) {
        return createNewDefaultQueue().saveMessage(message);
    }

    @Override
    public void setPushType(PushType pushType) {
        this.pushType = pushType;
    }

    @Override
    public void setReceiveType(ReceiveType receiveType) {
        this.receiveType = receiveType;
    }


}
