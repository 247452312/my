package indi.uhyils.util;

import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nonnull;
import org.slf4j.helpers.MessageFormatter;

/**
 * 反射工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时46分
 */
public final class ReflactUtil {

    private ReflactUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取实例中的某个属性的值
     *
     * @param obj      实例
     * @param attrName 属性
     *
     * @return 属性的值
     *
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getAttrByGetMethod(Object obj, String attrName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        if (attrName != null && attrName.length() > 1) {
            Method declaredMethod = obj.getClass().getDeclaredMethod("get" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1));
            declaredMethod.setAccessible(Boolean.TRUE);
            return declaredMethod.invoke(obj);
        } else {
            return null;
        }
    }

    /**
     * 用field获取类某个属性的名称
     *
     * @param object
     * @param attrName
     *
     * @return
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static Object getAttrByField(Object object, String attrName) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = object.getClass().getDeclaredField(attrName);
        declaredField.setAccessible(Boolean.TRUE);
        return declaredField.get(object);
    }

    /**
     * 获取某个方法的返回值的泛型
     *
     * @return 所有泛型
     */
    public static Class getGenericity(Method method) throws ClassNotFoundException {
        method.setAccessible(Boolean.TRUE);
        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            //因为list泛型只有一个值 所以直接取0下标
            String typeName = actualTypeArguments[0].getTypeName();
            //真实返回值类型 Class对象
            return Class.forName(typeName);
        }
        return null;
    }

    private static String format(@Nonnull String message, Object... args) {
        return MessageFormatter.arrayFormat(message, args).getMessage();
    }

    public static <T> T newInstance(Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            LogUtil.error(e, format("Create new instance of {} failed: {}", tClass, e.getMessage()));
            throw new RuntimeException(e);
        }

    }

    /**
     * 获取function的名称
     *
     * @param func
     * @param <T>
     * @param <R>
     *
     * @return
     *
     * @throws Exception
     */
    public static <T, R> java.lang.invoke.SerializedLambda doSFunction(SFunction<T, R> func) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 直接调用writeReplace
        Method writeReplace = func.getClass().getDeclaredMethod("writeReplace");
        writeReplace.setAccessible(true);
        //反射调用
        Object sl = writeReplace.invoke(func);
        return (java.lang.invoke.SerializedLambda) sl;
    }

    /**
     * 获取function的名称(下划线)
     *
     * @param func
     * @param <T>
     * @param <R>
     *
     * @return
     *
     * @throws Exception
     */
    public static <T, R> String transSFunction(SFunction<T, R> func) {
        SerializedLambda serializedLambda;
        try {
            serializedLambda = doSFunction(func);
            // 获取method名称
            String methodName = serializedLambda.getImplMethodName();
            // 转化为属性名称
            String fieldName = StringUtil.transMethodNameToFieldName(methodName);
            // 转化为field名称
            return StringUtil.toUnderline(fieldName);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            LogUtil.error(e);
        }
        return null;
    }
}
