package indi.uhyils.util;

import com.google.common.collect.Maps.EntryTransformer;
import indi.uhyils.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

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
        return putIfAbsent(map, key, valueSupplier, false);
    }

    /**
     * 同{@link Map#putIfAbsent(java.lang.Object, java.lang.Object)} 只是防止提前使用
     *
     * @param map           map本p
     * @param key           key
     * @param valueSupplier 执行后返回目标值的方法
     * @param returnOld     是否返回旧值
     * @param <K>           key的类型
     * @param <V>           目标值的类型
     *
     * @return 如果map中存在, 则使用map中的, 如果不存在, 则使用supplier返回的
     */
    @Nullable
    public static <V, K> V putIfAbsent(Map<K, V> map, K key, Supplier<V> valueSupplier, boolean returnOld) {
        V v = map.get(key);
        if (v != null) {
            return v;
        }
        V value = valueSupplier.get();
        V old = map.put(key, value);
        return returnOld ? old : value;

    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        if (map == null) {
            return false;
        }
        return map.size() != 0;
    }

    public static boolean isEmpty(Map map) {
        return !isNotEmpty(map);
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

    /**
     * putAll 但是映射
     *
     * @param target   put的目标map
     * @param source   源map
     * @param keyMap   key值映射
     * @param valueMap value值映射
     * @param <TK>
     * @param <TV>
     * @param <SK>
     * @param <SV>
     */
    public static <TK, TV, SK, SV> void putAllWithLink(Map<TK, TV> target, Map<SK, SV> source, EntryTransformer<SK, SV, TK> keyMap, EntryTransformer<SK, SV, TV> valueMap) {
        for (Entry<SK, SV> sksvEntry : source.entrySet()) {
            SK key = sksvEntry.getKey();
            SV value = sksvEntry.getValue();
            target.put(keyMap.transformEntry(key, value), valueMap.transformEntry(key, value));
        }
    }


    /**
     * copyMap
     *
     * @param data
     *
     * @return
     */
    public static <K, V> Map<K, V> copy(Map<K, V> data) {
        return copy(data, new HashMap<>());
    }

    private static <K, V> Map<K, V> copy(Map<K, V> data, HashMap<K, V> kvHashMap) {
        for (Entry<K, V> kvEntry : data.entrySet()) {
            kvHashMap.put(kvEntry.getKey(), kvEntry.getValue());
        }
        return kvHashMap;
    }


}
