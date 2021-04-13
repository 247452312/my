package indi.uhyils.core.topic;

import indi.uhyils.core.message.Message;

/**
 * topic
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 16时05分
 * @Version 1.0
 */
public class NormalTopic extends AbstractTopic {


    public NormalTopic(String name) {
        super(name);
    }

    public static NormalTopic build(String name, OutDealTypeEnum pushType, OutDealTypeEnum receiveType) {
        NormalTopic build = new NormalTopic(name);
        build.setPushType(pushType);
        build.setReceiveType(receiveType);
        return build;

    }

    @Override
    public void setPushType(OutDealTypeEnum pushType) {
        this.pushType = pushType;
    }

    @Override
    public void setReceiveType(OutDealTypeEnum receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    protected Boolean saveMessage0(Message message) {
        return createNewDefaultQueue().saveMessage(message);
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.NORMAL_MSG;
    }

}
