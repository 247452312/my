package indi.uhyils.rpc.exchange.pojo.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.rpc.exception.ContentArrayQuantityMismatchException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.enum_.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.RpcContent;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.util.LogUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求内容装载工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 14时09分
 */
public class RpcRequestContentFactory {

    /**
     * 内容大小
     */
    private static final Integer CONTENT_MIN_SIZE = 5;

    public static RpcContent createByContentArray(RpcData rpcData, String[] contentArray) throws ClassNotFoundException, RpcException {
        if (contentArray.length < CONTENT_MIN_SIZE) {
            throw new ContentArrayQuantityMismatchException(contentArray.length, CONTENT_MIN_SIZE);
        }
        RpcNormalRequestContent content = new RpcNormalRequestContent(rpcData);
        content.setServiceName(contentArray[RpcRequestContentEnum.SERVICE_NAME.getLine()]);
        content.setServiceVersion(contentArray[RpcRequestContentEnum.SERVICE_VERSION.getLine()]);
        content.setMethodName(contentArray[RpcRequestContentEnum.METHOD_NAME.getLine()]);
        String[] methodParamterTypes = contentArray[RpcRequestContentEnum.METHOD_PARAM_TYPE.getLine()].split(";");
        content.setMethodParamterTypes(methodParamterTypes);
        JSONArray argsMap = JSON.parseArray(contentArray[RpcRequestContentEnum.ARG_MAP.getLine()]);
        List<Object> args = new ArrayList<>();
        Class<?> serviceClass = Class.forName(content.getServiceName());
        Class<?>[] methodParamterClass = new Class[methodParamterTypes.length];
        for (int i = 0; i < methodParamterTypes.length; i++) {
            String classPath = methodParamterTypes[i];
            if (StringUtils.isEmpty(classPath)) {
                continue;
            }
            Class<?> clazz = Class.forName(classPath);
            methodParamterClass[i] = clazz;
        }
        try {
            Method method = serviceClass.getMethod(content.getMethodName(), methodParamterClass);
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            for (int i = 0; i < genericParameterTypes.length; i++) {
                Object o = JSON.parseObject(argsMap.get(i).toString(), genericParameterTypes[i]);
                args.add(o);
            }
        } catch (NoSuchMethodException e) {
            LogUtil.error(e);
        }
        content.setArgs(args.toArray());

        List<Object> others = new ArrayList<>();
        for (int i = 5; i < contentArray.length; i++) {
            String s = contentArray[i];
            Object parse = JSON.parse(s);
            others.add(parse);
        }
        content.setOthers(others.toArray());
        return content;
    }
}
