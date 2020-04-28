package indi.uhyils.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年04月27日 16时46分
 */
public class ReflactUtil {


    public static Object getAttr(Object obj, String attrName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //TODO 没有防止下标越界
        Method declaredMethod = obj.getClass().getDeclaredMethod("get" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1), null);
        return declaredMethod.invoke(obj, null);

    }
}
