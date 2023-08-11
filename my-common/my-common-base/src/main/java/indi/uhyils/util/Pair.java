package indi.uhyils.util;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年08月10日 14时44分
 */
public class Pair<K, V> {

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Pair() {
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
