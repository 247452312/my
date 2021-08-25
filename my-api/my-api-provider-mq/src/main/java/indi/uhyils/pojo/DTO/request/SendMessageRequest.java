package indi.uhyils.pojo.DTO.request;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.enum_.TopicType;


/**
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 18时12分
 * @Version 1.0
 */
public class SendMessageRequest extends DefaultLinkRequest {

    /**
     * 实际类型
     */
    private JSONObject data;

    /**
     * key
     */
    private String key;

    /**
     * 主题
     */
    private String topic;

    /**
     * 类型
     */
    private TopicType type;

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public TopicType getType() {
        return type;
    }

    public void setType(TopicType type) {
        this.type = type;
    }
}
