package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 消费过滤表(ConsumerFilter)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分03秒
 */
@TableName(value = "sys_consumer_filter")
public class ConsumerFilterDO extends BaseDO {

    private static final long serialVersionUID = -83949597483431263L;


    /**
     * 消费方id
     */
    @TableField
    private Long consumerId;

    /**
     * 接口表id
     */
    @TableField
    private Long interfaceId;

    /**
     * 规则
     */
    @TableField
    private String rule;


    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }


    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
    }


    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

}
