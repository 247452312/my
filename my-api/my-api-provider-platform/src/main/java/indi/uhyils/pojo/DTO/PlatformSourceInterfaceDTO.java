package indi.uhyils.pojo.DTO;

import indi.uhyils.pojo.DTO.base.IdDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 接口资源表表(PlatformSourceInterface)表 对外数据传输载体
 *
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2022年03月11日 15时16分
 */
public class PlatformSourceInterfaceDTO extends IdDTO {

    private static final long serialVersionUID = -1L;

    /**
     * 结果类型demo 同param_type
     */
    private String returnType;

    /**
     * 请求类型,例如HTTP中的post请求或者get请求
     */
    private String requestType;

    /**
     * 接口名称
     */
    private String interfaceName;

    /**
     * url地址
     */
    private String url;

    /**
     * 入参类型demo json形式,实际使用时入参需要匹配此规则
     */
    private String paramType;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 资源主表id
     */
    private Long sourceId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("returnType", getReturnType())
            .append("requestType", getRequestType())
            .append("interfaceName", getInterfaceName())
            .append("url", getUrl())
            .append("paramType", getParamType())
            .append("methodName", getMethodName())
            .append("id", getId())
            .append("sourceId", getSourceId())
            .toString();
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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

}
