package indi.uhyils.util;

import java.util.function.Supplier;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 16时31分
 */
@FunctionalInterface
public interface SupplierWithException<T> {


    /**
     * 此方法类似于{@link Supplier#get()} 只是内部的异常可以抛出来
     *
     * @return
     */
    T get() throws Throwable;

}
