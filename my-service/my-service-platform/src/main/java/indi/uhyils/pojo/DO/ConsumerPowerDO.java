package indi.uhyils.pojo.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import indi.uhyils.pojo.DO.base.BaseDO;

/**
 * 消费方权限表(ConsumerPower)表 数据库实体类
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 19时06分04秒
 */
@TableName(value = "sys_consumer_power")
public class ConsumerPowerDO extends BaseDO {

    private static final long serialVersionUID = 613982669677947020L;


    /**
     * 消费者id
     */
    @TableField
    private Long consumerId;

    /**
     * 接口权限id
     */
    @TableField
    private Long powerId;

    /**
     * 状态(0->申请中 1->已拥有)
     */
    @TableField
    private Integer status;


    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }


    public Long getPowerId() {
        return powerId;
    }

    public void setPowerId(Long powerId) {
        this.powerId = powerId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
