package indi.uhyils.pojo.DTO.response.trace;

import java.io.Serializable;
import java.util.List;


/**
 * 一个链路
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月31日 21时11分
 */
public class OneTraceLink implements Serializable {

    /**
     * traceId
     */
    private Long traceId;

    /**
     * 节点
     */
    private List<OneTraceNode> list;

    public static OneTraceLink build(Long traceId, List<OneTraceNode> list) {
        OneTraceLink build = new OneTraceLink();
        build.traceId = traceId;
        build.list = list;
        return build;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public List<OneTraceNode> getList() {
        return list;
    }

    public void setList(List<OneTraceNode> list) {
        this.list = list;
    }
}
