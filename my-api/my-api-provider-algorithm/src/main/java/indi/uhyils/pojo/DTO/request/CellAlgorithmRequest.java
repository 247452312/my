package indi.uhyils.pojo.DTO.request;


import indi.uhyils.pojo.cqe.DefaultCQE;

/**
 * 调用算法请求值 这个类中包含了请求算法的所有需要的东西
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月04日 17时15分
 */
public class CellAlgorithmRequest extends DefaultCQE {

    /**
     * 算法id
     */
    private Long algorithmId;

    /**
     * 请求体
     */
    private Object[] requestBody;


    public Long getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Long algorithmId) {
        this.algorithmId = algorithmId;
    }

    public Object[] getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object[] requestBody) {
        this.requestBody = requestBody;
    }
}
