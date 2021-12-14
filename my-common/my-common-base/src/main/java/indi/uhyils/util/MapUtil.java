package indi.uhyils.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import indi.uhyils.annotation.Nullable;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年06月12日 17时55分
 */
public final class MapUtil {

    private MapUtil() {
    }

    /**
     * 同{@link Map#putIfAbsent(java.lang.Object, java.lang.Object)} 只是防止提前使用
     *
     * @param map           map本p
     * @param key           key
     * @param valueSupplier 执行后返回目标值的方法
     * @param <K>           key的类型
     * @param <V>           目标值的类型
     *
     * @return 如果map中存在, 则使用map中的, 如果不存在, 则使用supplier返回的
     */
    @Nullable
    public static <V, K> V putIfAbsent(Map<K, V> map, K key, Supplier<V> valueSupplier) {
        V v = map.get(key);
        if (v == null) {
            v = map.put(key, valueSupplier.get());
        }

        return v;
    }

    public static boolean isNotEmpty(Map map) {
        if (map == null) {
            return false;
        }
        return map.size() != 0;
    }

    /**
     * 批量插入,每个判断
     *
     * @param mainMap
     * @param bePutMap
     * @param <T>
     */
    public static <T> void putAllIfAbsent(Map<String, T> mainMap, Map<String, T> bePutMap) {
        for (Entry<String, T> entry : bePutMap.entrySet()) {
            String key = entry.getKey();
            if (mainMap.containsKey(key)) {
                continue;
            }
            mainMap.put(key, entry.getValue());
        }
    }
}
