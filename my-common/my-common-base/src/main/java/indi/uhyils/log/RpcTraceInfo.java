package indi.uhyils.log;

import java.io.Serializable;
import java.util.List;


/**
 * rpcHeader中传递的日志表示信息
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月25日 21时31分
 */
public class RpcTraceInfo implements Serializable {

    /**
     * 链路唯一标示
     */
    private Long traceId;

    /**
     * 链路跟踪标识
     */
    private List<Integer> rpcIds;

    public static RpcTraceInfo build(Long traceId, List<Integer> rpcIds) {
        RpcTraceInfo build = new RpcTraceInfo();
        build.traceId = traceId;
        build.rpcIds = rpcIds;
        return build;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public List<Integer> getRpcIds() {
        return rpcIds;
    }

    public void setRpcIds(List<Integer> rpcIds) {
        this.rpcIds = rpcIds;
    }
}
