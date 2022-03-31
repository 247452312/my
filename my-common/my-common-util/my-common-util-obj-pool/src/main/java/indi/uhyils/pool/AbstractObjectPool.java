package indi.uhyils.pool;

import indi.uhyils.util.LogUtil;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 实例池模板
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年03月31日 14时39分
 */
public abstract class AbstractObjectPool<T> implements ObjectPool<T> {

    private static final String REFERENT_FIELD_NAME = "referent";

    /**
     * 实例池核心大小
     */
    private final Integer size;

    /**
     * 实例的class
     */
    private final Class<T> objClass;

    /**
     * 实例池本身
     */
    private final Queue<T> queue;

    /**
     * 虚引用指向
     */
    private final List<PhantomReference<T>> phantomReferenceList;

    /**
     * 虚引用gc队列
     */
    private final ReferenceQueue<T> referenceQueue;

    protected AbstractObjectPool(Integer size, Class<T> objClass) {
        this.size = size;
        this.objClass = objClass;
        queue = new ArrayDeque<>(size);
        phantomReferenceList = new ArrayList<>(size);
        referenceQueue = new ReferenceQueue<>();
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            T obj = createObject();
            queue.add(obj);
            phantomReferenceList.add(new PhantomReference<>(obj, referenceQueue));
        }
    }

    private T createObject() {
        try {
            return objClass.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            LogUtil.error(this, e, objClass.getName() + ",没有无入参的构造函数");
            return null;
        } catch (Exception e) {
            LogUtil.error(this, e);
            return null;
        }
    }

    @Override
    public T getOrCreateObject() {
        // 转移reference中的数据
        transReference();
        if (queue.isEmpty()) {
            return createObject();
        }
        return queue.poll();
    }

    /**
     * 转移数据
     */
    private void transReference() {
        Reference<? extends T> poll;
        try {
            while ((poll = referenceQueue.poll()) != null) {
                T t = getBeGcObj(poll);
                // 清空obj
                emptyObj(t);
                queue.add(t);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LogUtil.error(this, e);
        }
    }

    /**
     * 获取被gc的数据
     *
     * @param poll
     *
     * @return
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private T getBeGcObj(Reference<? extends T> poll) throws NoSuchFieldException, IllegalAccessException {
        Field referent = Reference.class.getDeclaredField(REFERENT_FIELD_NAME);
        referent.setAccessible(true);
        return (T) referent.get(poll);
    }

    /**
     * 清空 以及归还之前需要做的事情
     *
     * @param t
     */
    protected abstract void emptyObj(T t);


    @Override
    public long remainderCount() {
        return queue.size();
    }
}
