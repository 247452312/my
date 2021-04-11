package indi.uhyils.core.message;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.util.SequenceGenerationException;
import indi.uhyils.util.SequenceUtil;

/**
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

    protected AbstractMessage(String topic) {
        this.topic = topic;
        try {
            initSequence();
        } catch (InterruptedException | SequenceGenerationException e) {
            e.printStackTrace();
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

    private void initSequence() throws InterruptedException, SequenceGenerationException {
        this.sequence = SequenceUtil.newSequence();
    }


}
