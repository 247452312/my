package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultRequest;


/**
 * 获取一条信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 18时10分
 */
public class GetMessageRequest extends DefaultRequest {

    /**
     * 主题名称
     */
    private String topicName;

    /**
     * key名称 如果是分区队列才用这个
     */
    private String key;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
