package indi.uhyils.pojo.DTO.response.trace;

import indi.uhyils.pojo.DTO.TraceInfoDTO;
import java.io.Serializable;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时53分
 */
public class OneTraceNode implements Serializable {


    /**
     * 下一层layer
     */
    private List<OneTraceNode> lastLayerTrace;

    /**
     * 信息
     */
    private TraceInfoDTO traceInfoEntity;

    public List<OneTraceNode> getLastLayerTrace() {
        return lastLayerTrace;
    }

    public void setLastLayerTrace(List<OneTraceNode> lastLayerTrace) {
        this.lastLayerTrace = lastLayerTrace;
    }

    public TraceInfoDTO getTraceInfoEntity() {
        return traceInfoEntity;
    }

    public void setTraceInfoEntity(TraceInfoDTO traceInfoEntity) {
        this.traceInfoEntity = traceInfoEntity;
    }

}
