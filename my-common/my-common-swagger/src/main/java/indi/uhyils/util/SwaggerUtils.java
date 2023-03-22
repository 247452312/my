package indi.uhyils.util;

import com.google.common.base.Objects;
import indi.uhyils.pojo.DTO.MethodSwaggerDTO;
import indi.uhyils.pojo.DTO.ModelFieldInfoDTO;
import indi.uhyils.pojo.DTO.ModelInfoDTO;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月14日 16时07分
 */
public final class SwaggerUtils {

    /**
     * 解析一个类, 解析出rpc对应的方法信息
     * rpc类中的所有方法均为可以通过rpc进行调用的方法,所以需要解析所有方法
     *
     * @param targetClass
     * @param <T>
     *
     * @return
     */
    public static <T> List<MethodSwaggerDTO> parseToRpcMethods(Class<T> targetClass) {
        Method[] declaredMethods = targetClass.getDeclaredMethods();
        return Arrays.asList(declaredMethods).stream().filter(t->!t.getName().contains("$")).map(SwaggerUtils::parseToRpcMethod).collect(Collectors.toList());
    }

    /**
     * 见{@link SwaggerUtils#parseToRpcMethods(Class)},单体解析方法
     *
     * @param method
     *
     * @return
     */
    public static MethodSwaggerDTO parseToRpcMethod(Method method) {
        MethodSwaggerDTO rpcMethodSwaggerDTO = new MethodSwaggerDTO();
        Annotation[] annotations = method.getAnnotations();
        rpcMethodSwaggerDTO.setAnnotations(Arrays.stream(annotations).map(t -> t.annotationType().getName()).collect(Collectors.toList()));
        rpcMethodSwaggerDTO.setName(method.getName());
        rpcMethodSwaggerDTO.setResult(parseToModel(method.getReturnType()));
        rpcMethodSwaggerDTO.setParams(parseToFields(method.getParameterTypes()));
        return rpcMethodSwaggerDTO;
    }

    /**
     * 解析一个类为字段信息
     *
     * @param returnType
     *
     * @return
     */
    public static ModelInfoDTO parseToModel(Class<?> returnType) {
        ModelInfoDTO modelInfoDTO = new ModelInfoDTO();
        modelInfoDTO.setAnnotation(Arrays.stream(returnType.getAnnotations()).map(t -> t.annotationType().getName()).collect(Collectors.toList()));
        modelInfoDTO.setName(returnType.getName());
        List<Method> getMethod = Arrays.stream(returnType.getDeclaredMethods()).filter(t -> t.getName().startsWith("get")).collect(Collectors.toList());
        modelInfoDTO.setFields(parseGetMethodToFields(returnType, getMethod));
        return modelInfoDTO;
    }

    /**
     * 解析多个类为字段信息
     *
     * @param returnType
     *
     * @return
     */
    private static List<ModelInfoDTO> parseToFields(Class<?>... returnType) {
        return parseToFields(Arrays.asList(returnType));
    }

    /**
     * 将get方法 解析为字段
     *
     * @param getMethod
     *
     * @return
     */
    private static List<ModelFieldInfoDTO> parseGetMethodToFields(Class<?> clazz, List<Method> getMethod) {
        return getMethod.stream().map(t -> parseGetMethodToField(clazz, t)).collect(Collectors.toList());
    }

    /**
     * 解析get方法为字段
     *
     * @param method
     *
     * @return
     */
    private static ModelFieldInfoDTO parseGetMethodToField(Class<?> clazz, Method method) {
        String name = StringUtil.firstToLowerCase(method.getName().substring("get".length()));

        Class<?> returnType = method.getReturnType();

        ModelFieldInfoDTO modelFieldInfoDTO = new ModelFieldInfoDTO();
        modelFieldInfoDTO.setType(returnType.getName());
        modelFieldInfoDTO.setName(name);

        for (Field field : clazz.getFields()) {
            if (!Objects.equal(field.getName(), name)) {
                continue;
            }
            List<String> annotations = Arrays.stream(field.getAnnotations()).map(t -> t.annotationType().getName()).collect(Collectors.toList());
            modelFieldInfoDTO.setAnnotations(annotations);
            break;
        }
        return modelFieldInfoDTO;
    }

    /**
     * 解析多个类为字段信息
     *
     * @param asList
     *
     * @return
     */
    private static List<ModelInfoDTO> parseToFields(List<Class<?>> asList) {
        return asList.stream().map(SwaggerUtils::parseToModel).collect(Collectors.toList());
    }
}
