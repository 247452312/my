package indi.uhyils.rpc.netty.callback.impl;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.netty.callback.RpcRequestCallback;
import indi.uhyils.rpc.netty.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.netty.enums.RpcStatusEnum;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.netty.pojo.RpcHeader;
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
public class RpcDefaultCallBack implements RpcRequestCallback {
    @Override
    public RpcData invoke(RpcContent content) throws RpcException, ClassNotFoundException {
        if (content instanceof RpcRequestContent) {
            RpcRequestContent requestContent = (RpcRequestContent) content;
            // 执行方法
            String execute = execute(requestContent);

            RpcHeader rpcHeader = new RpcHeader();
            rpcHeader.setName("default-value");
            rpcHeader.setValue("value");
            RpcHeader[] rpcHeaders = {rpcHeader};
            String responseType;
            if (execute == null) {
                responseType = RpcResponseTypeEnum.EXCEPTION.getCode().toString();
            } else if (StringUtils.isEmpty(execute)) {
                responseType = RpcResponseTypeEnum.NULL_BACK.getCode().toString();
            } else {
                responseType = RpcResponseTypeEnum.STRING_BACK.getCode().toString();
            }
            return Objects.requireNonNull(RpcFactoryProducer.build(RpcTypeEnum.RESPONSE))
                    .createByInfo(new Object[]{RpcStatusEnum.OK.getCode()}, rpcHeaders, responseType, execute);
        }
        return null;
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
