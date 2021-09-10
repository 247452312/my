package indi.uhyils.netty.model;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import java.io.Serializable;

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
     * 解析并发送
     */
    private Maps.EntryTransformer<ChannelHandlerContext, Object, ChannelFuture> function;

    /**
     * 需要执行service
     */
    private Boolean needService;

    /**
     * 返回需要的值
     */
    private Supplier<byte[]> returnByteFunction;

    /**
     * 是否是长连接
     */
    private boolean keepAlive;

    public static ProtocolParsingModel buildServiceModel(String protocolName, String ip, boolean keepAlive, String methodName, Class[] paramsType, Object[] params, Maps.EntryTransformer<ChannelHandlerContext, Object, ChannelFuture> function) {
        ProtocolParsingModel build = new ProtocolParsingModel();
        build.protocolName = protocolName;
        build.ip = ip;
        build.keepAlive = keepAlive;
        build.methodName = methodName;
        build.paramsType = paramsType;
        build.params = params;
        build.function = function;
        build.needService = Boolean.TRUE;
        return build;
    }

    public static ProtocolParsingModel buildReturnModel(String protocolName, String ip, boolean keepAlive, Supplier<byte[]> returnByteFunction) {
        ProtocolParsingModel build = new ProtocolParsingModel();
        build.keepAlive = keepAlive;
        build.protocolName = protocolName;
        build.ip = ip;
        build.needService = Boolean.FALSE;
        build.returnByteFunction = returnByteFunction;
        return build;
    }

    public Boolean getNeedService() {
        return needService;
    }

    public void setNeedService(Boolean needService) {
        this.needService = needService;
    }

    public Supplier<byte[]> getReturnByteFunction() {
        return returnByteFunction;
    }

    public void setReturnByteFunction(Supplier<byte[]> returnByteFunction) {
        this.returnByteFunction = returnByteFunction;
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

    public Maps.EntryTransformer<ChannelHandlerContext, Object, ChannelFuture> getFunction() {
        return function;
    }

    public void setFunction(Maps.EntryTransformer<ChannelHandlerContext, Object, ChannelFuture> function) {
        this.function = function;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isNotKeepAlive() {
        return !keepAlive;
    }
}
