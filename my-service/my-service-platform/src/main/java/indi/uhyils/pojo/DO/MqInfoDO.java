package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.enum_.InterfaceTypeEnum;

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

    /**
     * MQ类型(0->rocketMQ 1->rabbitMQ)
     */
    @TableField
    private Integer type;

    /**
     * 用户名
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 端口
     */
    @TableField
    private Integer port;

    /**
     * rocketMQ用
     */
    @TableField
    private String accessKey;

    /**
     * rocketMQ用
     */
    @TableField
    private String secretKey;


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public InterfaceTypeEnum getSourceType() {
        return InterfaceTypeEnum.MQ;
    }
}
