package indi.uhyils.pojo.response;

import indi.uhyils.pojo.model.TraceInfoEntity;
import java.io.Serializable;
import java.util.List;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月01日 13时28分
 */
public class GetLinkByTraceIdResponse implements Serializable {

    private List<TraceInfoEntity> data;

    public static GetLinkByTraceIdResponse build(List<TraceInfoEntity> data) {
        GetLinkByTraceIdResponse build = new GetLinkByTraceIdResponse();
        build.data = data;
        return build;
    }

    public List<TraceInfoEntity> getData() {
        return data;
    }

    public void setData(List<TraceInfoEntity> data) {
        this.data = data;
    }
}
