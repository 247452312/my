package indi.uhyils.util;


import java.util.Collection;
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

    public static <T, E> Boolean contains(Collection<T> collection, E e, Function<T, E> function) {
        if (collection == null) {
            return false;
        }
        return collection.stream().map(function).anyMatch(t -> t.equals(e));
    }
}
