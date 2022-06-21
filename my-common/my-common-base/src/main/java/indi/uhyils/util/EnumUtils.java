package indi.uhyils.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理enum信息的工具
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年06月13日 12时44分
 */
public final class EnumUtils {


    /**
     * get方法的公共前缀
     */
    private static final String GET_METHOD_PREFIX = "get";

    private EnumUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Map<String, Object>> getEnumList(Class en) {
        List<Map<String, Object>> result = new ArrayList<>();
        Enum[] enumConstants = (Enum[]) en.getEnumConstants();
        Method[] methods = en.getMethods();
        List<Method> getMethods = new ArrayList<>();
        Arrays.stream(methods).forEach(method -> {
            if (method.getName().startsWith(GET_METHOD_PREFIX)) {
                getMethods.add(method);
            }
        });
        Arrays.stream(enumConstants).forEach(anEnum -> {
            Map<String, Object> map = new HashMap<>(getMethods.size());
            getMethods.forEach(getMethod -> {
                try {
                    Object invoke = getMethod.invoke(anEnum);
                    String substring = getMethod.getName().substring(3);
                    String fieldName = substring.substring(0, 1).toLowerCase() + substring.substring(1);
                    map.put(fieldName, invoke);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LogUtil.error(EnumUtils.class, e);
                }
            });
            result.add(map);
        });

        return result;
    }
}
