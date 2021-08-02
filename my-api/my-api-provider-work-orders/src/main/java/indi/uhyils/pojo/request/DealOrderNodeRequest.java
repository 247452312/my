package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;
import java.util.Map;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年11月09日 19时30分
 */
public class DealOrderNodeRequest extends DefaultRequest {

    /**
     * 填充的节点属性值<节点属性id,真实值>
     */
    Map<Long, Object> orderNodeFieldValueMap;

    /**
     * 节点id
     */
    private Long nodeId;

    /**
     * 处理结果id
     */
    private Long resultId;

    /**
     * 处理人建议
     */
    private String suggest;

    public Map<Long, Object> getOrderNodeFieldValueMap() {
        return orderNodeFieldValueMap;
    }

    public void setOrderNodeFieldValueMap(Map<Long, Object> orderNodeFieldValueMap) {
        this.orderNodeFieldValueMap = orderNodeFieldValueMap;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
