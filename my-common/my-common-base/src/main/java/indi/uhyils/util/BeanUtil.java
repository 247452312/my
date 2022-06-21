package indi.uhyils.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.CollectionUtils;

/**
 * @author uhyils <247452312@qq.com>
 * @version 1.0
 * @date 文件创建日期 2021年08月25日 21时27分
 */
public final class BeanUtil {

    private static final Cache<String, BeanCopier> BEAN_COPIER_CACHE;

    static {
        BEAN_COPIER_CACHE = CacheBuilder.newBuilder().expireAfterAccess(1L, TimeUnit.HOURS).build();
    }

    private BeanUtil() {
    }

    public static <T> T copyProperties(@Nonnull Object source, @Nonnull Class<T> target) {
        T t = ReflactUtil.newInstance(target);
        copyProperties(source, t);
        return t;
    }

    public static <T> T copyProperties(@Nonnull Object source, @Nonnull T target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass());
        copier.copy(source, target, null);
        return target;
    }

    public static <T> List<T> copyPropertiesOfList(@Nonnull List<?> sourceList, @Nonnull List<T> targetList, @Nonnull Class<T> targetType) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        } else {
            Iterator var3 = sourceList.iterator();

            while (var3.hasNext()) {
                Object obj = var3.next();
                targetList.add(copyProperties(obj, targetType));
            }

            return targetList;
        }
    }

    public static <T> List<T> copyPropertiesOfList(@Nonnull List<?> sourceList, @Nonnull Class<T> targetType) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return Lists.newArrayList();
        } else {
            List<T> targetList = new ArrayList(sourceList.size());
            copyPropertiesOfList(sourceList, targetList, targetType);
            return targetList;
        }
    }

    private static BeanCopier getBeanCopier(Class<?> sourceClass, Class<?> targetClass) {
        String beanKey = generateKey(sourceClass, targetClass);
        BeanCopier copier = BEAN_COPIER_CACHE.getIfPresent(beanKey);
        if (Objects.isNull(copier)) {
            copier = BeanCopier.create(sourceClass, targetClass, false);
            BEAN_COPIER_CACHE.put(beanKey, copier);
        }

        return copier;
    }

    private static String generateKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + targetClass.getName();
    }
}
