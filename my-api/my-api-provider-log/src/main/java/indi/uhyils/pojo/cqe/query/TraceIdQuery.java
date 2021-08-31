package indi.uhyils.pojo.cqe.query;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月29日 17时35分
 */
public class TraceIdQuery extends AbstractArgQuery {


    private Long TraceId;

    public Long getTraceId() {
        return TraceId;
    }

    public void setTraceId(Long traceId) {
        TraceId = traceId;
    }
}
