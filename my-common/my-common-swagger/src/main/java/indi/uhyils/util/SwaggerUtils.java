package indi.uhyils.util;

import com.google.common.base.Objects;
import indi.uhyils.annotation.Public;
import indi.uhyils.annotation.ReadWriteMark;
import indi.uhyils.pojo.DTO.MethodSwaggerDTO;
import indi.uhyils.pojo.DTO.ModelFieldInfoDTO;
import indi.uhyils.pojo.DTO.ModelInfoDTO;
import indi.uhyils.pojo.DTO.ReadWriteMarkInfo;
import io.swagger.annotations.ApiModelProperty;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

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
        return Arrays.stream(declaredMethods).filter(t -> !t.getName().contains("$")).map(t -> parseToRpcMethod(targetClass, t)).collect(Collectors.toList());
    }


    /**
     * 见{@link SwaggerUtils#parseToRpcMethods(Class)},单体解析方法
     *
     * @param method
     *
     * @return
     */
    public static MethodSwaggerDTO parseToRpcMethod(Class<?> targetClass, Method method) {
        Annotation[] annotations = method.getAnnotations();
        MethodSwaggerDTO rpcMethodSwaggerDTO = new MethodSwaggerDTO();
        ReadWriteMarkInfo readWriteMark = null;

        boolean publicFlag = false;
        for (Annotation annotation : annotations) {
            if (Objects.equal(annotation.annotationType(), Public.class)) {
                publicFlag = true;
            }
            if (Objects.equal(annotation.annotationType(), ReadWriteMark.class)) {
                ReadWriteMark readWriteMarkAnn = (ReadWriteMark) annotation;
                readWriteMark = new ReadWriteMarkInfo();
                readWriteMark.setType(readWriteMarkAnn.type());
                readWriteMark.setTables(Arrays.asList(readWriteMarkAnn.tables()));
                readWriteMark.setCacheType(readWriteMarkAnn.cacheType());
            }
        }
        if (readWriteMark == null) {
            ReadWriteMark annotation = targetClass.getAnnotation(ReadWriteMark.class);
            if (annotation != null) {
                readWriteMark = new ReadWriteMarkInfo();
                readWriteMark.setType(annotation.type());
                readWriteMark.setTables(Arrays.asList(annotation.tables()));
                readWriteMark.setCacheType(annotation.cacheType());
            }
        }
        rpcMethodSwaggerDTO.setPublicFlag(publicFlag);
        rpcMethodSwaggerDTO.setReadWriteMark(readWriteMark);
        rpcMethodSwaggerDTO.setName(method.getName());
        rpcMethodSwaggerDTO.setResultType(parseToModel(method.getGenericReturnType()));
        rpcMethodSwaggerDTO.setParamTypes(parseToModels(method.getGenericParameterTypes()));

        return rpcMethodSwaggerDTO;
    }

    /**
     * 解析一个没有名称的类
     *
     * @param type
     *
     * @return
     */
    private static ModelInfoDTO parseToModel(Type type) {
        if (ModelNoteThreadLocalContext.contains(type.getTypeName())) {
            return ModelInfoDTO.build(type.getTypeName());
        }
        return ModelNoteThreadLocalContext.tryInit(() -> {
            ModelNoteThreadLocalContext.add(type.getTypeName());

            try {
                ModelInfoDTO result;
                if (type instanceof ParameterizedType) {
                    return parseParameterizedTypeToModel((ParameterizedType) type);
                } else if ((result = dealBasicType(type)) != null) {
                    // 基本类型直接返回
                    return result;
                } else if (type instanceof Class<?>) {
                    return parseClassToModel((Class<?>) type);
                }
            } finally {
                ModelNoteThreadLocalContext.remove(type.getTypeName());
            }
            Asserts.throwException("错误的类型:{}", type.getClass().getName());
            return null;
        });
    }

    /**
     * 处理基本类型
     *
     * @param type
     *
     * @return
     */
    private static ModelInfoDTO dealBasicType(Type type) {
        String typeName = type.getTypeName();
        if (!isBasicType(typeName)) {
            return null;
        }
        ModelInfoDTO modelInfoDTO = new ModelInfoDTO();
        modelInfoDTO.setFields(null);
        modelInfoDTO.setType(typeName);
        modelInfoDTO.setSimpleType(typeName);
        return modelInfoDTO;
    }

    /**
     * 判断一个类型是否是不需要解析的类型
     *
     * @param className
     *
     * @return
     */
    private static Boolean isBasicType(String className) {
        if ("void".equalsIgnoreCase(className)) {
            return true;
        } else if (className.startsWith("java.")) {
            return true;
        }
        return false;
    }

    /**
     * 解析一个不带泛型的类为model对象
     *
     * @param clazz
     *
     * @return
     */
    private static ModelInfoDTO parseClassToModel(Class<?> clazz) {
        if (Collection.class.isAssignableFrom(clazz)) {
            ModelInfoDTO modelInfoDTO = new ModelInfoDTO();
            modelInfoDTO.setType(clazz.getName());
            modelInfoDTO.setSimpleType(clazz.getSimpleName());
            return modelInfoDTO;
        }
        List<Method> getMethod = Arrays.stream(clazz.getDeclaredMethods()).filter(t -> t.getName().startsWith("get")).collect(Collectors.toList());

        ModelInfoDTO modelInfoDTO = new ModelInfoDTO();
        modelInfoDTO.setFields(parseGetMethodToFields(clazz, getMethod));
        modelInfoDTO.setType(clazz.getName());
        modelInfoDTO.setSimpleType(clazz.getSimpleName());
        modelInfoDTO.setName(null);
        return modelInfoDTO;
    }

    /**
     * 解析多个类为字段信息
     *
     * @param returnType
     *
     * @return
     */
    private static List<ModelInfoDTO> parseToModels(Type... returnType) {
        return parseToModels(Arrays.asList(returnType));
    }

    /**
     * 解析一个带有泛型的类为model对象
     *
     * @param type
     *
     * @return
     */
    private static ModelInfoDTO parseParameterizedTypeToModel(ParameterizedType type) {

        Class<?> clazz = (Class<?>) type.getRawType();
        ModelInfoDTO modelInfoDTO = parseToModel(clazz);
        Type[] actualTypeArguments = type.getActualTypeArguments();
        modelInfoDTO.setSchema(Arrays.stream(actualTypeArguments).map(t -> ((Class<?>) t).getName()).collect(Collectors.toList()));
        modelInfoDTO.setSchemaSimple(Arrays.stream(actualTypeArguments).map(t -> ((Class<?>) t).getSimpleName()).collect(Collectors.toList()));
        modelInfoDTO.setChildTypes(parseToModels(actualTypeArguments));
        return modelInfoDTO;
    }

    /**
     * 解析多个类为字段信息
     *
     * @param asList
     *
     * @return
     */
    private static List<ModelInfoDTO> parseToModels(List<Type> asList) {
        return asList.stream().map(SwaggerUtils::parseToModel).collect(Collectors.toList());
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

        Type genericReturnType = method.getGenericReturnType();
        ModelInfoDTO modelFieldInfoDTO = parseToModel(genericReturnType);
        ModelFieldInfoDTO result = new ModelFieldInfoDTO();

        result.setType(modelFieldInfoDTO.getType());
        result.setChildTypes(modelFieldInfoDTO.getChildTypes());
        result.setSimpleType(modelFieldInfoDTO.getSimpleType());
        result.setSchema(modelFieldInfoDTO.getSchema());
        result.setSchemaSimple(modelFieldInfoDTO.getSchemaSimple());
        result.setName(name);

        for (Field field : clazz.getDeclaredFields()) {
            if (!Objects.equal(field.getName(), name)) {
                continue;
            }
            List<String> annotations = Arrays.stream(field.getAnnotations()).map(t -> t.annotationType().getName()).collect(Collectors.toList());
            if (annotations.contains(ApiModelProperty.class.getName())) {
                ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                String desc = annotation.value();
                result.setDesc(desc);
            }
            if (annotations.contains(NotNull.class.getSimpleName())) {
                result.setRequired(true);
            } else if (annotations.contains(ApiModelProperty.class.getSimpleName())) {
                ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
                result.setRequired(annotation.required());
            }
            break;
        }
        return result;
    }

}
