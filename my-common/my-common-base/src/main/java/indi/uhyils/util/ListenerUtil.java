package indi.uhyils.util;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 监听工具类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月03日 07时15分
 */
public class ListenerUtil {

    /**
     * 添加监听
     *
     * @param call              要执行的方法,必须有一个返回值
     * @param callback          方法执行完成的返回值的处理方法
     * @param exceptionCallBack 发生错误时的操作
     * @param <T>               中间变量返回值
     */
    public static <T> void addListening(Supplier<T> call, Consumer<T> callback, Function<Throwable, Void> exceptionCallBack) {
        CompletableFuture.supplyAsync(call).thenAccept(callback).exceptionally(exceptionCallBack);
    }

    /**
     * 添加监听
     * 默认的回调方法,只是打印了日志,没有其余操作
     *
     * @param call     要执行的方法,必须有一个返回值
     * @param callback 方法执行完成的返回值的处理方法
     * @param <T>      中间变量返回值
     */
    public static <T> void addListening(Supplier<T> call, Consumer<T> callback) {
        CompletableFuture.supplyAsync(call).thenAccept(callback).exceptionally(throwable -> {
            LogUtil.error(ListenerUtil.class, throwable);
            return null;
        });
    }


}
