package indi.uhyils.rpc.netty.callback.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.netty.enums.RpcStatusEnum;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.exception.RpcVersionNotSupportedException;
import indi.uhyils.rpc.netty.pojo.*;
import indi.uhyils.rpc.netty.pojo.request.RpcRequestContent;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 这个只是一个样例,具体如何执行要看下一级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 19时15分
 */
public class RpcDefaultRequestCallBack implements RpcCallBack {
    @Override
    public RpcData getRpcData(byte[] data) throws RpcException, ClassNotFoundException {
        /*解析*/
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        // 获取到的Request
        assert build != null;

        return build.createByBytes(data);
    }

    @Override
    public RpcContent getContent(byte[] data) throws RpcException, ClassNotFoundException {
        RpcData request = getRpcData(data);
        Integer version = request.rpcVersion();

        if (version > MyRpcContent.VERSION) {
            throw new RpcVersionNotSupportedException(version, MyRpcContent.VERSION);
        }
        return request.content();

    }

    @Override
    public String invoke(RpcContent content) {
        if (content instanceof RpcRequestContent) {
            RpcRequestContent requestContent = (RpcRequestContent) content;
            // 执行方法
            return execute(requestContent);
        }
        return null;
    }

    @Override
    public RpcData assembly(Long unique, String result) throws RpcException, ClassNotFoundException {
        RpcHeader rpcHeader = new RpcHeader();
        rpcHeader.setName("default-value");
        rpcHeader.setValue("value");
        RpcHeader[] rpcHeaders = {rpcHeader};
        String responseType;
        if (result == null) {
            responseType = RpcResponseTypeEnum.EXCEPTION.getCode().toString();
        } else if (StringUtils.isEmpty(result)) {
            responseType = RpcResponseTypeEnum.NULL_BACK.getCode().toString();
        } else {
            responseType = RpcResponseTypeEnum.STRING_BACK.getCode().toString();
        }
        return Objects.requireNonNull(RpcFactoryProducer.build(RpcTypeEnum.RESPONSE))
                .createByInfo(unique, new Object[]{RpcStatusEnum.OK.getCode()}, rpcHeaders, responseType, result);
    }

    /**
     * 执行方法
     *
     * @param requestContent
     * @return
     */
    private String execute(RpcRequestContent requestContent) {
        try {
            Class<?> clazz = Class.forName(requestContent.getServiceName());
            Object bean = clazz.newInstance();
            String[] methodParameterTypes = requestContent.getMethodParameterTypes();
            Class[] methodClass = new Class[methodParameterTypes.length];
            for (int i = 0; i < methodParameterTypes.length; i++) {
                methodClass[i] = Class.forName(methodParameterTypes[i]);
            }
            Method declaredMethod = clazz.getDeclaredMethod(requestContent.getMethodName(), methodClass);
            Object invoke = declaredMethod.invoke(bean, requestContent.getArgs());
            return invoke == null ? "" : JSON.toJSONString(invoke);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
