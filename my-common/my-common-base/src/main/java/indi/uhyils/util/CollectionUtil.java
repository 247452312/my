package indi.uhyils.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月21日 11时15分
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length != 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 是否包含
     *
     * @param collection 集合
     * @param e          目标
     * @param function   判断相等
     * @param <T>        集合中的类型
     * @param <E>        要比较的类型
     *
     * @return
     */
    public static <T, E> Boolean contains(Collection<T> collection, E e, Function<T, E> function) {
        if (collection == null) {
            return false;
        }
        return collection.stream().map(function).anyMatch(t -> t.equals(e));
    }

    /**
     * 构造一个arrayList,可以remove等操作
     *
     * @param baseEvent
     * @param <T>
     *
     * @return
     */
    @SafeVarargs
    public static <T> List<T> buildArrayList(T... baseEvent) {
        List<T> result = new ArrayList<>(baseEvent.length);
        Collections.addAll(result, baseEvent);
        return result;
    }

    /**
     * 给array添加新的元素
     *
     * @param arrays
     * @param arg
     * @param <T>
     *
     * @return
     */
    public static <T> T[] arrayAdd(T[] arrays, T... arg) {
        int arrayLength = arrays.length;
        T[] newArrays = Arrays.copyOf(arrays, arrayLength + arg.length);
        System.arraycopy(arrays, 0, newArrays, 0, arrayLength);
        System.arraycopy(arg, 0, newArrays, arrayLength, arg.length);
        return newArrays;
    }
}
