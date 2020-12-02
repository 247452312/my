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
    Map<String, Object> orderNodeFieldValueMap;
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 处理结果id
     */
    private String resultId;
    /**
     * 处理人建议
     */
    private String suggest;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Map<String, Object> getOrderNodeFieldValueMap() {
        return orderNodeFieldValueMap;
    }

    public void setOrderNodeFieldValueMap(Map<String, Object> orderNodeFieldValueMap) {
        this.orderNodeFieldValueMap = orderNodeFieldValueMap;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}
