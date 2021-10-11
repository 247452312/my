package indi.uhyils.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
}
