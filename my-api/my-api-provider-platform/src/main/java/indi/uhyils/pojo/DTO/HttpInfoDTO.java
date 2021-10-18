package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;

/**
 * http连接表(HttpInfo)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年10月18日 17时27分40秒
 */
public class HttpInfoDTO extends IdDTO {

    private static final long serialVersionUID = -63999310403784296L;


    /**
     * url地址
     */
    private String url;

    /**
     * 入参类型自定义规则
     */
    private String paramType;

    /**
     * 结果类型(demo)
     */
    private String returnType;

    /**
     * 请求类型
     */
    private String requestType;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }


    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

}
