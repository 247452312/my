package indi.uhyils.rpc.content;

import indi.uhyils.MyThreadLocal;
import java.util.HashMap;
import java.util.Map;

/**
 * 传递时的header暂存地
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年02月11日 09时08分
 */
public class RpcHeaderContext {


    /**
     * 标题头
     */
    private static volatile MyThreadLocal<Map<String, String>> headers = new MyThreadLocal<>();

    /**
     * 获取header
     *
     * @return
     */
    public static Map<String, String> get() {
        return headers.get();
    }

    /**
     * 批量添加header
     *
     * @param headerMap
     */
    public static void addHeaders(Map<String, String> headerMap) {
        if (headerMap == null) {
            return;
        }
        initHeaders();
        headers.get().putAll(headerMap);
    }

    /**
     * 添加header
     *
     * @param key
     * @param value
     */
    public static void addHeader(String key, String value) {
        initHeaders();
        headers.get().put(key, value);
    }

    /**
     * 是否包含
     *
     * @param key
     *
     * @return
     */
    public static Boolean contains(String key) {
        if (headers.get() == null) {
            return false;
        }
        return headers.get().containsKey(key);
    }

    /**
     * 清空
     */
    public static void remove() {
        headers.remove();
    }

    /**
     * 初始化header
     */
    private static void initHeaders() {
        if (headers.get() == null) {
            headers.set(new HashMap<>());
        }
    }


}
