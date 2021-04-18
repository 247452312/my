package indi.uhyils.core.message;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.exception.IdGenerationException;
import indi.uhyils.util.IdUtil;
import indi.uhyils.util.LogUtil;
import indi.uhyils.util.SpringUtil;

/**
 * 消息模板
 *
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 20时30分
 * @Version 1.0
 */
public abstract class AbstractMessage implements Message {
    /**
     * 数据
     */
    protected JSONObject data;

    /**
     * 主题
     */
    protected String topic;

    /**
     * 序列号
     */
    protected Long sequence;

    protected AbstractMessage(JSONObject data, String topic) {
        this.data = data;
        this.topic = topic;
        try {
            initSequence();
        } catch (InterruptedException | IdGenerationException e) {
            LogUtil.error(this, e);
        }
    }

    @Override
    public JSONObject getData() {
        return data;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public Long getSequence() {
        return sequence;
    }

    private void initSequence() throws IdGenerationException, InterruptedException {
        this.sequence = SpringUtil.getBean(IdUtil.class).newId();
    }

}
