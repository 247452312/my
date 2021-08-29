package indi.uhyils.pojo.DTO.response;

import indi.uhyils.pojo.DTO.base.ServiceResult;

import java.io.Serializable;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月30日 08时21分
 */
public class WebResponse<T> implements Serializable {

    private Integer code;

    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public static <T extends Serializable> WebResponse<T> build(T data, String message, Integer code) {
        WebResponse<T> serializableWebResponse = new WebResponse<>();
        serializableWebResponse.setCode(code);
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(message);
        return serializableWebResponse;
    }


    public static <T extends Serializable> WebResponse<T> build(ServiceResult<T> serviceResult) {
        return build(serviceResult.getData(), serviceResult.getServiceMessage(), serviceResult.getServiceCode());
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
