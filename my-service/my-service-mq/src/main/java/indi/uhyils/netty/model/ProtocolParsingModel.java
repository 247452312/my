package indi.uhyils.netty.model;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 协议解析生成的包
 * 
 * @Author uhyils <247452312@qq.com>
 * @Date 文件创建日期 2021年04月24日 09时41分
 * @Version 1.0
 */
public class ProtocolParsingModel implements Serializable {

    /**
     * 协议类型
     */
    private String protocolName;
    /**
     * ip
     */
    private String ip;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class[] paramsType;
    /**
     * 参数名称
     */
    private Object[] params;

    /**
     * 解析
     */
    private Function<Object, Object> function;

    public static ProtocolParsingModel build(String protocolName, String ip, String methodName, Class[] paramsType,
        Object[] params, Function<Object, Object> function) {
        ProtocolParsingModel build = new ProtocolParsingModel();
        build.protocolName = protocolName;
        build.ip = ip;
        build.methodName = methodName;
        build.paramsType = paramsType;
        build.params = params;
        build.function = function;
        return build;
    }

    public String getProtocolName() {
        return protocolName;
    }

    public void setProtocolName(String protocolName) {
        this.protocolName = protocolName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParamsType() {
        return paramsType;
    }

    public void setParamsType(Class[] paramsType) {
        this.paramsType = paramsType;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Function<Object, Object> getFunction() {
        return function;
    }

    public void setFunction(Function<Object, Object> function) {
        this.function = function;
    }
}
