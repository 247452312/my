package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.TraceDetailDO;
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
    private TraceDetailDO traceDetailEntity;

    public static GetTraceDetailByHashCodeResponse build(TraceDetailDO traceDetailEntity) {
        GetTraceDetailByHashCodeResponse build = new GetTraceDetailByHashCodeResponse();
        build.traceDetailEntity = traceDetailEntity;
        return build;
    }

    public TraceDetailDO getTraceDetailEntity() {
        return traceDetailEntity;
    }

    public void setTraceDetailEntity(TraceDetailDO traceDetailEntity) {
        this.traceDetailEntity = traceDetailEntity;
    }
}
