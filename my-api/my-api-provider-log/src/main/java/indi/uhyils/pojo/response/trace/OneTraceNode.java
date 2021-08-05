package indi.uhyils.pojo.response.trace;

import indi.uhyils.pojo.model.TraceInfoEntity;
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
    private TraceInfoEntity traceInfoEntity;

    public List<OneTraceNode> getLastLayerTrace() {
        return lastLayerTrace;
    }

    public void setLastLayerTrace(List<OneTraceNode> lastLayerTrace) {
        this.lastLayerTrace = lastLayerTrace;
    }

    public TraceInfoEntity getTraceInfoEntity() {
        return traceInfoEntity;
    }

    public void setTraceInfoEntity(TraceInfoEntity traceInfoEntity) {
        this.traceInfoEntity = traceInfoEntity;
    }

}