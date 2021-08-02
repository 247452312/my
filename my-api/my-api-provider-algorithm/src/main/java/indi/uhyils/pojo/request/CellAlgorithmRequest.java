package indi.uhyils.pojo.request;

import indi.uhyils.pojo.request.base.DefaultRequest;

/**
 * 调用算法请求值 这个类中包含了请求算法的所有需要的东西
 * 算法类还没有实现,等待之后的完善 TODO
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时15分
 */
public class CellAlgorithmRequest extends DefaultRequest {

    /**
     * 算法id
     */
    private Long algorithmId;

    /**
     * 此次请求id
     */
    private Long requestId;

    /**
     * 上一个请求的id
     */
    private Long lastRequestId;

    /**
     * 请求体
     */
    private Double[] requestBody;

    /**
     * base64请求体
     */
    private String base64;

    public Long getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getLastRequestId() {
        return lastRequestId;
    }

    public void setLastRequestId(Long lastRequestId) {
        this.lastRequestId = lastRequestId;
    }

    public Double[] getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Double[] requestBody) {
        this.requestBody = requestBody;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
