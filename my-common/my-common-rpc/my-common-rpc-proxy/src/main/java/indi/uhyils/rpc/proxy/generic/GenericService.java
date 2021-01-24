package indi.uhyils.rpc.proxy.generic;

import com.alibaba.fastjson.JSONObject;
import indi.uhyils.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

/**
 * 泛化接口
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月23日 09时42分
 */
public class GenericService<T> {
    /**
     * 此泛化接口代表的接口
     */
    private final T service;

    public GenericService(T service) throws Exception {
        this.service = service;
    }

    public T getService() {
        return service;
    }

    public Object invoke(String method, Class[] parameterTypes, Object[] args) throws InvocationTargetException {
        try {
            Method targetMethod = service.getClass().getMethod(method, parameterTypes);
            targetMethod.setAccessible(true);
            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = JSONObject.parseObject(JSONObject.toJSONString(args[i]), parameterTypes[i]);
            }
            return targetMethod.invoke(service, args);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            LogUtil.error(this, e);
        }
        return null;
    }

    public Object invoke(String method, String[] parameterTypes, Object[] args) throws InvocationTargetException {
        Class[] parameterTypesClass = new Class[parameterTypes.length];
        try {
            for (int i = 0; i < parameterTypes.length; i++) {
                parameterTypesClass[i] = Class.forName(parameterTypes[i]);
            }
        } catch (ClassNotFoundException e) {
            LogUtil.error(this, e);
        }
        return invoke(method, parameterTypesClass, args);
    }

    public CompletableFuture<Object> invokeAsync(String method, String[] parameterTypes, Object[] args) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return invoke(method, parameterTypes, args);
            } catch (InvocationTargetException e) {
                LogUtil.error(this, e);
            }
            return null;
        });
    }

}
