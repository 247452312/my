package indi.uhyils.core.topic;

import indi.uhyils.core.message.Message;
import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.exception.ExpressionInvalidException;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时47分
 * @Version 1.0
 */
public class GlobalTopic extends AbstractTopic {

    public GlobalTopic(String name) {
        super(name);
    }

    public static GlobalTopic build(String name, OutDealTypeEnum pushType, OutDealTypeEnum receiveType) {
        GlobalTopic build = new GlobalTopic(name);
        build.setPushType(pushType);
        build.setReceiveType(receiveType);
        return build;
    }

    @Override
    public TopicType getTopicType() {
        return TopicType.GLOBAL_SEQUENTIAL_MSG;
    }

    @Override
    protected Boolean saveMessage0(Message message) throws ExpressionInvalidException {
        return createOrGetDefaultQueue().saveMessage(message);
    }

    @Override
    public void setPushType(OutDealTypeEnum pushType) {
        this.pushType = pushType;
    }

    @Override
    public void setReceiveType(OutDealTypeEnum receiveType) {
        this.receiveType = receiveType;
    }


}
