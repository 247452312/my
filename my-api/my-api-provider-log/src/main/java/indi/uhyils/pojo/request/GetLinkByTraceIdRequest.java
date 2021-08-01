package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月01日 13时27分
 */
public class GetLinkByTraceIdRequest extends DefaultRequest {

    /**
     * traceId
     */
    private Long traceId;

    /**
     * rpc链路
     */
    private String rpcId;

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public String getRpcId() {
        return rpcId;
    }

    public void setRpcId(String rpcId) {
        this.rpcId = rpcId;
    }
}
