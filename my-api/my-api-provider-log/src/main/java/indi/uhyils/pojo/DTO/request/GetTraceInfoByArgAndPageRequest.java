package indi.uhyils.pojo.DTO.request;

import indi.uhyils.pojo.DTO.request.base.DefaultPageQuery;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月04日 08时39分
 */
public class GetTraceInfoByArgAndPageRequest extends DefaultPageQuery {

    /**
     * 链路唯一索引
     */
    private Long traceId;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * type
     */
    private Integer type;


    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
