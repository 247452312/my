package indi.uhyils.pojo.request;

import indi.uhyils.enum_.OutDealTypeEnum;
import indi.uhyils.enum_.TopicType;
import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 20时47分
 * @Version 1.0
 */
public class CreateTopicRequest extends DefaultRequest {

    /**
     * topic名称
     */
    private String name;

    /**
     * topic类型
     */
    private TopicType type;

    /**
     * 如果是分区消息,则传递此参数
     */
    private String key;

    /**
     * 接收类型
     */
    private OutDealTypeEnum receiveType;

    /**
     * 推送类型
     */
    private OutDealTypeEnum pushType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TopicType getType() {
        return type;
    }

    public void setType(TopicType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public OutDealTypeEnum getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(OutDealTypeEnum receiveType) {
        this.receiveType = receiveType;
    }

    public OutDealTypeEnum getPushType() {
        return pushType;
    }

    public void setPushType(OutDealTypeEnum pushType) {
        this.pushType = pushType;
    }
}
