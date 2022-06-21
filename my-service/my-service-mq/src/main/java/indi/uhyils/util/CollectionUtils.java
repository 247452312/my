package indi.uhyils.util;

import java.util.Collection;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年04月18日 15时33分
 */
public class CollectionUtils {

    /**
     * 判断是否为空或无
     *
     * @param collection
     *
     * @return
     */
    public static <T> Boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合不为空
     *
     * @param collection
     *
     * @return
     */
    public static <T> Boolean isNotEmpty(Collection<T> collection) {
        return collection != null && collection.size() != 0;
    }
}
