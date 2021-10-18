package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.enum_.SourceTypeEnum;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * mq连接信息表(MqInfo)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分08秒
 */
@TableName(value = "sys_mq_info")
public class MqInfoDO extends SourceInfoDO {

    private static final long serialVersionUID = 469102065830327112L;


    /**
     * mq地址
     */
    @TableField
    private String url;

    /**
     * MQ-topic
     */
    @TableField
    private String topic;

    /**
     * mq-tag
     */
    @TableField
    private String tag;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public SourceTypeEnum getSourceType() {
        return SourceTypeEnum.MQ;
    }
}
