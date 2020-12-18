package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 11时03分
 */
public class RpcNormalRequestContent implements RpcRequestContent {
    /**
     * 接口名称
     */
    private String serviceName;
    /**
     * 接口版本
     */
    private String serviceVersion;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 方法参数类型
     */
    private String[] methodParamterTypes;
    /**
     * 参数
     */
    private Object[] args;
    /**
     * 其他预留字段
     */
    private Object[] others;


    @Override
    public Integer type() {
        return RpcTypeEnum.REQUEST.getCode();
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String getServiceVersion() {
        return serviceVersion;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String[] getMethodParameterTypes() {
        return methodParamterTypes;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    @Override
    public Object[] getOthers() {
        return others;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setMethodParamterTypes(String[] methodParamterTypes) {
        this.methodParamterTypes = methodParamterTypes;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public void setOthers(Object[] others) {
        this.others = others;
    }
}
