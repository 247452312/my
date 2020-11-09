package indi.uhyils.pojo.response.base;

import indi.uhyils.enum_.ServiceCode;
import indi.uhyils.pojo.request.base.DefaultRequest;
import indi.uhyils.pojo.request.model.LinkNode;

import java.io.Serializable;

/**
 * 微服务返回类
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
     * 链路跟踪
     */
    private LinkNode<String> requestLink;


    public ServiceResult(T data, Integer serviceCode, String serviceMessage, LinkNode<String> linkNode) {
        this.data = data;
        this.serviceCode = serviceCode;
        this.serviceMessage = serviceMessage;
        this.requestLink = linkNode;
    }

    public ServiceResult(T data, Integer serviceCode, String serviceMessage, DefaultRequest req) {
        if (req == null) {
            throw new NullPointerException("请求参数为空");
        }
        this.data = data;
        this.serviceCode = serviceCode;
        this.serviceMessage = serviceMessage;
        this.requestLink = req.getRequestLink();
    }

    public ServiceResult() {
    }

    /**
     * 构建一个逻辑成功的返回
     *
     * @param businessMessage 给前台返回的信息
     * @param t               请求返回值
     * @param req             请求(链路跟踪用)
     * @param <T>             请求返回值
     * @return 一个code是200 代表成功的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildSuccessResult(String businessMessage, T t, DefaultRequest req) {
        return new ServiceResult(t, ServiceCode.SUCCESS.getText(), businessMessage, req);
    }

    /**
     * 构建一个逻辑成功的返回
     *
     * @param t   请求返回值
     * @param req 请求(链路跟踪用)
     * @param <T> 请求返回值
     * @return 一个code是200 代表成功的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildSuccessResult(T t, DefaultRequest req) {
        return new ServiceResult(t, ServiceCode.SUCCESS.getText(), null, req);
    }

    /**
     * 构建一个逻辑失败的返回
     *
     * @param businessMessage 给前台的返回
     * @param t               失败的返回体,通常是null
     * @param req             请求(链路跟踪用)
     * @param <T>             失败的返回体,通常是null
     * @return 一个code是400的 代表逻辑错误的返回(程序并没有错)
     */
    public static <T extends Serializable> ServiceResult<T> buildFailedResult(String businessMessage, T t, DefaultRequest req) {
        if (req == null) {
            req = new DefaultRequest();
        }
        return new ServiceResult(t, ServiceCode.REQUEST_PARAM_ERROR.getText(), businessMessage, req);
    }

    /**
     * 构建一个程序失败的返回
     *
     * @param businessMessage 给前台的返回
     * @param req             前台的请求
     * @param req             请求(链路跟踪用)
     * @param <T>             null
     * @return 一个code是500 代表逻辑错误的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildErrorResult(String businessMessage, DefaultRequest req) {
        if (req == null) {
            req = new DefaultRequest();
        }
        return new ServiceResult(null, ServiceCode.ERROR.getText(), businessMessage, req);
    }

    /**
     * 构建一个权限未通过的返回
     *
     * @param req 前台的请求
     * @param <T> null
     * @return 一个code是401 代表权限未通过的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildNoAuthResult(DefaultRequest req) {
        return buildResultByServiceCode(ServiceCode.NONE_AUTH_ERROR, req);
    }

    /**
     * 构建一个登录过期的返回
     *
     * @param req 前台的请求
     * @param <T> null
     * @return 一个code是402 代表登录问题的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildLoginOutResult(DefaultRequest req) {
        return buildResultByServiceCode(ServiceCode.LOGIN_TIME_OUT_ERROR, req);
    }

    /**
     * 构建一个未登录的返回
     *
     * @param req 前台的请求
     * @param <T> null
     * @return 一个code是403 代表登录问题的返回
     */
    public static <T extends Serializable> ServiceResult<T> buildNoLoginResult(DefaultRequest req) {
        return buildResultByServiceCode(ServiceCode.NO_LOGIN__ERROR, req);
    }

    /**
     * 根据ServiceCode构建一个返回
     *
     * @param req 前台的请求
     * @param <T> null
     * @return 一个返回
     */
    public static <T extends Serializable> ServiceResult<T> buildResultByServiceCode(ServiceCode code, DefaultRequest req) {
        return new ServiceResult(null, code.getText(), code.getMsg(), req);
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
}
