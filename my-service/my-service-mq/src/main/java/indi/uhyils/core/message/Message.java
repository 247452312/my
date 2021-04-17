package indi.uhyils.core.message;

import indi.uhyils.enum_.TopicType;
import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 消息体
 * 
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月11日 17时02分
 * @Version 1.0
 */
public interface Message extends Serializable {
    /**
     * 获取消息
     *
     * @return
     */
    JSONObject getData();

    /**
     * 获取主题
     *
     * @return
     */
    String getTopic();

    /**
     * 获取类型
     *
     * @return
     */
    TopicType getType();

    /**
     * 获取入队时间
     *
     * @return
     */
    Long getSequence();
}
