package indi.uhyils.rpc.content;

import indi.uhyils.MyThreadLocal;
import indi.uhyils.util.StringUtil;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月21日 09时53分
 */
public class ClusterNameContext {

    /**
     * 指定的集群名称
     */
    private static volatile MyThreadLocal<String> APPOINT_CLUSTER = new MyThreadLocal<>();


    /**
     * 获取header
     *
     * @return
     */
    public static String get() {
        return APPOINT_CLUSTER.get();
    }

    /**
     * 批量添加header
     *
     * @param clusterName
     */
    public static void add(String clusterName) {
        if (StringUtil.isEmpty(clusterName)) {
            return;
        }
        APPOINT_CLUSTER.set(clusterName);
    }

    /**
     * 清空
     */
    public static void remove() {
        APPOINT_CLUSTER.remove();
    }

}
