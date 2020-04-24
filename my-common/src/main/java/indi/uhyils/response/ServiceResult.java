package indi.uhyils.response;

import indi.uhyils.enum_.ResponseCode;
import indi.uhyils.request.DefaultRequest;

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

    public ServiceResult(T data, Integer serviceCode, String serviceMessage, String token) {
        this.data = data;
        this.serviceCode = serviceCode;
        this.serviceMessage = serviceMessage;
        this.token = token;
    }

    public ServiceResult() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 构建一个成功的返回
     *
     * @param businessMessage
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> ServiceResult buildSuccessResult(String businessMessage, T t, DefaultRequest req) {
        return new ServiceResult(t, ResponseCode.SUCCESS.getText(), businessMessage, req.getToken());
    }

    /**
     * 构建一个失败的返回
     *
     * @param businessMessage
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Serializable> ServiceResult buildFailedResult(String businessMessage, T t, DefaultRequest req) {
        return new ServiceResult(t, ResponseCode.FAILED.getText(), businessMessage, req.getToken());
    }
}
