package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DO.TraceInfoDO;
import java.io.Serializable;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时04分
 */
public class GetAllLinkByTraceIdResponse implements Serializable {

    /**
     * trace信息
     */
    private List<TraceInfoDO> traceInfos;

    public static GetAllLinkByTraceIdResponse build(List<TraceInfoDO> traceInfos) {
        GetAllLinkByTraceIdResponse build = new GetAllLinkByTraceIdResponse();
        build.traceInfos = traceInfos;
        return build;
    }

    public List<TraceInfoDO> getTraceInfos() {
        return traceInfos;
    }

    public void setTraceInfos(List<TraceInfoDO> traceInfos) {
        this.traceInfos = traceInfos;
    }
}
