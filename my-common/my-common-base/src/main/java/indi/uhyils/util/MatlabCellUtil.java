package indi.uhyils.util;

import java.lang.reflect.Method;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年07月19日 09时24分
 */
public class MatlabCellUtil {

    /**
     * 调用matlab函数 注 项目必须引入matlab jar包和打包的matlab程序jar包
     *
     * @return
     */
    public static Object[] matlabCell(String className, String methodName, Object... param) throws Exception {
        Class<?> aClass = Class.forName(className);
        Object o = aClass.newInstance();
        Method declaredMethod = aClass.getDeclaredMethod(methodName, int.class, java.lang.Object[].class);
        if (declaredMethod.getName().equalsIgnoreCase(methodName)) {
            return (Object[]) declaredMethod.invoke(o, param.length, param);
        }
        throw new Exception("没有找到Method");
    }
}
