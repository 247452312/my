package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DO.TraceInfoDO;
import java.io.Serializable;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月01日 13时28分
 */
public class GetLinkByTraceIdAndRpcIdResponse implements Serializable {

    private List<TraceInfoDO> data;

    public static GetLinkByTraceIdAndRpcIdResponse build(List<TraceInfoDO> data) {
        GetLinkByTraceIdAndRpcIdResponse build = new GetLinkByTraceIdAndRpcIdResponse();
        build.data = data;
        return build;
    }

    public List<TraceInfoDO> getData() {
        return data;
    }

    public void setData(List<TraceInfoDO> data) {
        this.data = data;
    }
}
