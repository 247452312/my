package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.TraceDetailEntity;
import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月04日 09时10分
 */
public class GetTraceDetailByHashCodeResponse implements Serializable {

    /**
     * trace
     */
    private TraceDetailEntity traceDetailEntity;

    public static GetTraceDetailByHashCodeResponse build(TraceDetailEntity traceDetailEntity) {
        GetTraceDetailByHashCodeResponse build = new GetTraceDetailByHashCodeResponse();
        build.traceDetailEntity = traceDetailEntity;
        return build;
    }

    public TraceDetailEntity getTraceDetailEntity() {
        return traceDetailEntity;
    }

    public void setTraceDetailEntity(TraceDetailEntity traceDetailEntity) {
        this.traceDetailEntity = traceDetailEntity;
    }
}
