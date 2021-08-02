package indi.uhyils.pojo.request;

import indi.uhyils.enum_.OutDealTypeEnum;

/**
 * 注册消费者
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 21时21分
 * @Version 1.0
 */
public class RegisterConsumerRequest extends DefaultLinkRequest {

    /**
     * topic名称
     */
    private String topicName;


    /**
     * 行为
     */
    private OutDealTypeEnum behavior;

    public OutDealTypeEnum getBehavior() {
        return behavior;
    }

    public void setBehavior(OutDealTypeEnum behavior) {
        this.behavior = behavior;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

}
