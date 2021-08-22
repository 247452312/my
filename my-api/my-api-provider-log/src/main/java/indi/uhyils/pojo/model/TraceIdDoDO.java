package indi.uhyils.pojo.model;

import indi.uhyils.pojo.model.base.BaseDoDO;


/**
 * 拥有traceId的表
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月03日 08时15分
 */
public class TraceIdDoDO extends BaseDoDO {

    /**
     * 链路唯一id
     */
    private Long traceId;

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }
}
