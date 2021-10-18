package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * 消费过滤表(ConsumerFilter)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分39秒
 */
public class ConsumerFilterDTO extends IdDTO {

    private static final long serialVersionUID = -74843570504912812L;


    /**
     * 消费方id
     */
    private Long consumerId;

    /**
     * 接口表id
     */
    private Long interfaceId;

    /**
     * 规则
     */
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
