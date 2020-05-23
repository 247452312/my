package indi.uhyils.pojo.response;

import indi.uhyils.enum_.ResponseCode;
import indi.uhyils.pojo.request.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;

import java.io.Serializable;

/**
 * 返回类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 14时17分
 */
public class ServiceResult<T extends Serializable> implements Serializable {


    /**
     * 返回的类型
     */
    private T data;

    /**
     * 服务状态码.
     */
    private Integer serviceCode;

    /**
     * 服务提示给用户的信息.
     */
    private String serviceMessage;

    /**
     * 用户信息
     */
    private String token;

    /**
     * 链路跟踪
     */
    private LinkNode<String> requestLink;


    public ServiceResult(T data, Integer serviceCode, String serviceMessage, String token, LinkNode linkNode) {
        this.data = data;
        this.serviceCode = serviceCode;
        this.serviceMessage = serviceMessage;
        this.token = token;
        this.requestLink = linkNode;
    }

    public ServiceResult(T data, Integer serviceCode, String serviceMessage, DefaultRequest req) {
        if (req == null) {
            throw new NullPointerException("请求参数为空");
        }
        this.data = data;
        this.serviceCode = serviceCode;
        this.serviceMessage = serviceMessage;
        this.token = req.getToken();
        this.requestLink = req.getRequestLink();
    }

    public ServiceResult() {
    }

    /**
     * 构建一个逻辑成功的返回
     *
     * @param businessMessage
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> ServiceResult buildSuccessResult(String businessMessage, T t, DefaultRequest req) {
        return new ServiceResult(t, ResponseCode.SUCCESS.getText(), businessMessage, req);
    }

    /**
     * 构建一个逻辑失败的返回
     *
     * @param businessMessage
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> ServiceResult buildFailedResult(String businessMessage, T t, DefaultRequest req) {
        return new ServiceResult(t, ResponseCode.REQUEST_PARM_ERROR.getText(), businessMessage, req);
    }

    /**
     * 构建一个程序失败的返回
     *
     * @param businessMessage
     * @param req
     * @param <T>
     * @return
     */
    public static <T extends Serializable> ServiceResult buildErrorResult(String businessMessage, DefaultRequest req) {
        return new ServiceResult(null, ResponseCode.ERROR.getText(), businessMessage, req);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceMessage() {
        return serviceMessage;
    }

    public void setServiceMessage(String serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    public LinkNode<String> getRequestLink() {
        return requestLink;
    }

    public void setRequestLink(LinkNode<String> requestLink) {
        this.requestLink = requestLink;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
