package indi.uhyils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年06月27日 15时01分
 */
public class MyThreadLocal<T> extends InheritableThreadLocal<T> {

    private static Map<Thread, Map<MyThreadLocal<?>, Object>> cacheMyThreadLocal = new HashMap<>();

    public MyThreadLocal() {
    }

    public static Map<MyThreadLocal<?>, Object> copy() {
        Thread t = Thread.currentThread();
        return cacheMyThreadLocal.get(t);
    }

    /**
     * 初始化这个线程run之前的搜歌threadLocal
     *
     * @param cache
     */
    public static void initChildThreadLocal(Map<MyThreadLocal<?>, Object> cache) {
        cacheMyThreadLocal.put(Thread.currentThread(), cache);
    }

    /**
     * 删除线程相关的所有threadLocal
     */
    public static void removeChildThreadLocal() {
        cacheMyThreadLocal.remove(Thread.currentThread());
    }


    @Override
    public T get() {
        Thread thread = Thread.currentThread();
        Optional<Map<MyThreadLocal<?>, Object>> myThreadLocalObjectMap = Optional.ofNullable(cacheMyThreadLocal.get(thread));
        return myThreadLocalObjectMap.map(t -> (T) t.get(this)).orElse(initialValue());
    }

    @Override
    public void set(T value) {
        Thread thread = Thread.currentThread();
        Map<MyThreadLocal<?>, Object> myThreadLocalObjectMap = cacheMyThreadLocal.computeIfAbsent(thread, k -> new HashMap<>());
        myThreadLocalObjectMap.put(this, value);
    }

    @Override
    public void remove() {
        Thread thread = Thread.currentThread();
        Map<MyThreadLocal<?>, Object> myThreadLocalObjectMap = cacheMyThreadLocal.get(thread);
        if (myThreadLocalObjectMap == null) {
            return;
        }
        myThreadLocalObjectMap.remove(this);
    }
}
