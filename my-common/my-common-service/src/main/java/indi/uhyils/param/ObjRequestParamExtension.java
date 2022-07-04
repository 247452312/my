package indi.uhyils.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import indi.uhyils.pojo.DTO.base.BaseDTO;
import indi.uhyils.pojo.cqe.command.base.AddCommand;
import indi.uhyils.rpc.annotation.RpcSpi;
import indi.uhyils.rpc.spi.param.ParamTransExtension;
import indi.uhyils.util.ClassUtil;
import indi.uhyils.util.LogUtil;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
        Type genericParameterType = methodNameMethod.get(0).getGenericParameterTypes()[methodTypeIndex];
        boolean addCommandType = parameterType.equals(AddCommand.class);
        if (addCommandType) {
            ParameterizedType realParamType = (ParameterizedType) genericParameterType;
            String requestJson = JSON.toJSONString(arg);
            String paramTypeName = realParamType.getTypeName();
            if (paramTypeName.contains(GENERIC_LEFT_BRACKET)) {
                String substring = paramTypeName.substring(paramTypeName.indexOf(GENERIC_LEFT_BRACKET) + 1, paramTypeName.lastIndexOf(GENERIC_RIGHT_BRACKET));
                if (PARADIGM_STRING.equals(substring)) {
                    Type genericInterface = null;
                    try {
                        genericInterface = ClassUtil.getRealObj(bean).getClass().getGenericInterfaces()[0];
                    } catch (Exception e) {
                        LogUtil.error(e);
                        return arg;
                    }
                    paramTypeName = genericInterface.getTypeName();
                    substring = paramTypeName.substring(paramTypeName.indexOf(GENERIC_LEFT_BRACKET) + 1, paramTypeName.lastIndexOf(GENERIC_RIGHT_BRACKET));
                }
                if (addCommandType) {
                    AddCommand objRequest = JSONObject.parseObject(requestJson, AddCommand.class);
                    BaseDTO o = (BaseDTO) JSONObject.parseObject(JSON.toJSONString(objRequest.getDto()), Class.forName(substring));
                    objRequest.setDto(o);

                    return objRequest;

                }
                //                else if (objsRequestEquals) {
                //                    ObjsRequest<Serializable> objsRequest = JSONObject.parseObject(requestJson, ObjsRequest.class);
                //                    List<Serializable> list = objsRequest.getList();
                //                    List<Serializable> targetList = new ArrayList<>(list.size());
                //                    for (Serializable serializable : list) {
                //                        targetList.add((Serializable) JSONObject.parseObject(JSON.toJSONString(serializable), Class.forName(substring)));
                //                    }
                //                    objsRequest.setList(targetList);
                //                    return objsRequest;
                //                }
            }
        }
        return arg;
    }
}
