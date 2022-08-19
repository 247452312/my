package indi.uhyils.util;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月21日 11时15分
 */
public final class CollectionUtil {

    private CollectionUtil() {
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
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

    /**
     * 复制数组
     *
     * @param array  原始数组
     * @param pos    偏移
     * @param length 长度
     * @param <T>
     *
     * @return
     */
    public static <T> T[] arrayCopy(T[] array, int pos, int length) {
        Asserts.assertTrue(isNotEmpty(array));

        final Class<? extends Object[]> newType = array.getClass();
        T[] copy = (newType == Object[].class)
            ? (T[]) new Object[length]
            : (T[]) Array.newInstance(newType.getComponentType(), length);
        System.arraycopy(array, pos, copy, 0, Math.min(array.length, length));
        return copy;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 复制数组
     *
     * @param array 原始数组
     * @param pos   偏移
     * @param <T>
     *
     * @return
     */
    public static <T> T[] arrayCopy(T[] array, int pos) {
        Asserts.assertTrue(isNotEmpty(array));
        final int length = array.length - pos;

        final Class<? extends Object[]> newType = array.getClass();
        T[] copy = (newType == Object[].class)
            ? (T[]) new Object[length]
            : (T[]) Array.newInstance(newType.getComponentType(), length);
        System.arraycopy(array, pos, copy, 0, length);
        return copy;
    }

    /**
     * 浅拷贝列表
     *
     * @param result
     * @param <T>
     *
     * @return
     */
    public static <T> List<T> copyList(List<T> result) {
        return result.stream().collect(Collectors.toList());
    }
}
