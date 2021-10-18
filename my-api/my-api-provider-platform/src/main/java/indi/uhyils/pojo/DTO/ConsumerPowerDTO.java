package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 消费方权限表(ConsumerPower)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class ConsumerPowerDTO extends IdDTO {

    private static final long serialVersionUID = 932421938210562824L;


    /**
     * 消费者id
     */
    private Long consumerId;

    /**
     * 接口权限id
     */
    private Long powerId;

    /**
     * 状态(0->申请中 1->已拥有)
     */
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
