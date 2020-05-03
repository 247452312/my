package indi.uhyils.response;

import java.io.Serializable;

/**
 * 从前台后端返回前台前端的返回
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月23日 16时57分
 */
public class WebResponse<T extends Serializable> implements Serializable {

    /**
     * token
     */
    private String token;

    private Integer code;

    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static <T extends Serializable> WebResponse build(T data, String token, String message, Integer code) {
        WebResponse serializableWebResponse = new WebResponse();
        serializableWebResponse.setCode(code);
        serializableWebResponse.setData(data);
        serializableWebResponse.setMsg(message);
        serializableWebResponse.setToken(token);
        return serializableWebResponse;
    }

    public static <T extends Serializable> WebResponse build(ServiceResult serviceResult) {
        return build(serviceResult.getData(), serviceResult.getToken(), serviceResult.getServiceMessage(), serviceResult.getServiceCode());
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
