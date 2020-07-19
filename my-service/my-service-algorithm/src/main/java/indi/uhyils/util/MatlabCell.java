package indi.uhyils.util;

import com.mathworks.toolbox.javabuilder.MWException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月19日 09时24分
 */
public class MatlabCell {

    /**
     * 调用matlab函数
     *
     * @return
     */
    public static Object[] matlabCell(String className, String methodName, Object... param) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Class<?> aClass = Class.forName(className);
        Object o = aClass.newInstance();
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equalsIgnoreCase(methodName)) {
                return (Object[]) declaredMethod.invoke(o, param.length, param);
            }
        }
        return null;
    }
}
