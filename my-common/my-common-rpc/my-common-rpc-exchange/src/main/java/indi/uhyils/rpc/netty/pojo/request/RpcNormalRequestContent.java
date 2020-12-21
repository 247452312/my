package indi.uhyils.rpc.netty.pojo.request;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.AbstractRpcObserverAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 11时03分
 */
public class RpcNormalRequestContent extends AbstractRpcObserverAdapter implements RpcRequestContent {
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

    public RpcNormalRequestContent(RpcData rpcData) {
        setRpcData(rpcData);
    }

    @Override
    public Integer type() {
        return RpcTypeEnum.REQUEST.getCode();
    }

    @Override
    public String execute() {
        try {
            Class<?> clazz = Class.forName(serviceName);
//            Object bean = SpringUtil.getBean(clazz);这里之后会改
            Object bean = clazz.newInstance();
            Class[] methodClass = new Class[methodParamterTypes.length];
            for (int i = 0; i < methodParamterTypes.length; i++) {
                methodClass[i] = Class.forName(methodParamterTypes[i]);
            }
            Method declaredMethod = clazz.getDeclaredMethod(methodName, methodClass);
            Object invoke = declaredMethod.invoke(bean, args);
            return JSON.toJSONString(invoke);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return "{\"error\":\" " + serviceName + " 找不到\"}";
        }
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String[] getMethodParameterTypes() {
        return methodParamterTypes;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public Object[] getOthers() {
        return others;
    }

    public void setOthers(Object[] others) {
        this.others = others;
    }

    public void setMethodParamterTypes(String[] methodParamterTypes) {
        this.methodParamterTypes = methodParamterTypes;
    }
}
