package indi.uhyils.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.request.base.ObjRequest;
import indi.uhyils.pojo.request.base.ObjsRequest;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.spi.param.ParamTransExtension;
import indi.uhyils.util.ClassUtil;
import indi.uhyils.util.LogUtil;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;


/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年07月12日 21时08分
 */
@RpcSpi
public class ObjRequestParamExtension implements ParamTransExtension {

    /**
     * 泛型T
     */
    private static final String PARADIGM_STRING = "T";

    /**
     * 泛型左括号
     */
    private static final String GENERIC_LEFT_BRACKET = "<";

    /**
     * 泛型右括号
     */
    private static final String GENERIC_RIGHT_BRACKET = ">";

    @Override
    public Object changeObjRequestParadigm(Object arg, Class interfaceClass, Method method, Integer methodTypeIndex, Object bean) throws ClassNotFoundException {
        Class<?> realClass = null;
        try {
            realClass = ClassUtil.getRealClass(bean);
        } catch (Exception e) {
            LogUtil.error(this, e);
        }
        if (realClass == null) {
            return arg;
        }
        Class<?> parameterType = method.getParameterTypes()[methodTypeIndex];
        final Method finalMethod = method;
        List<Method> methodNameMethod = Arrays.stream(realClass.getMethods()).filter(t -> t.getName().equals(finalMethod.getName()))
                                              .filter(t -> t.getParameterTypes()[methodTypeIndex].equals(parameterType)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(methodNameMethod)) {
            return arg;
        }
        method = methodNameMethod.get(0);
        boolean objRequestEquals = parameterType.equals(ObjRequest.class);
        boolean objsRequestEquals = parameterType.equals(ObjsRequest.class);
        if (objRequestEquals || objsRequestEquals) {
            String requestJson = JSON.toJSONString(arg);
            Type[] genericInterfaces = method.getGenericParameterTypes();
            Type genericSuperclass = genericInterfaces[0];
            String className = genericSuperclass.getTypeName();
            if (className.contains(GENERIC_LEFT_BRACKET)) {
                String substring = className.substring(className.indexOf(GENERIC_LEFT_BRACKET) + 1, className.lastIndexOf(GENERIC_RIGHT_BRACKET));
                if (PARADIGM_STRING.equals(substring)) {
                    Type genericInterface = interfaceClass.getGenericInterfaces()[0];
                    className = genericInterface.getTypeName();
                    substring = className.substring(className.indexOf(GENERIC_LEFT_BRACKET) + 1, className.lastIndexOf(GENERIC_RIGHT_BRACKET));
                }
                if (objRequestEquals) {
                    ObjRequest<Serializable> objRequest = JSONObject.parseObject(requestJson, ObjRequest.class);
                    objRequest.setData((Serializable) JSONObject.parseObject(JSON.toJSONString(objRequest.getData()), Class.forName(substring)));

                    return objRequest;

                } else if (objsRequestEquals) {
                    ObjsRequest<Serializable> objsRequest = JSONObject.parseObject(requestJson, ObjsRequest.class);
                    List<Serializable> list = objsRequest.getList();
                    List<Serializable> targetList = new ArrayList<>(list.size());
                    for (Serializable serializable : list) {
                        targetList.add((Serializable) JSONObject.parseObject(JSON.toJSONString(serializable), Class.forName(substring)));
                    }
                    objsRequest.setList(targetList);
                    return objsRequest;
                }
            }
        }
        return arg;
    }
}
