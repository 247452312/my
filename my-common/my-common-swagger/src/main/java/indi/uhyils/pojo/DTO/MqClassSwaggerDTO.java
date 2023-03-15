package indi.uhyils.pojo.DTO;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 15时07分
 */
public class MqClassSwaggerDTO extends ClassSwaggerDTO {

    @ApiModelProperty("mq topic")
    private String topic;

    @ApiModelProperty("监听的tag")
    private List<String> tag;

    @ApiModelProperty("消费者信息")
    private String consumerInfo;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public String getConsumerInfo() {
        return consumerInfo;
    }

    public void setConsumerInfo(String consumerInfo) {
        this.consumerInfo = consumerInfo;
    }
}
