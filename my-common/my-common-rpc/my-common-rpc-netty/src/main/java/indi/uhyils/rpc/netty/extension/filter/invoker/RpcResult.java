package indi.uhyils.rpc.netty.extension.filter.invoker;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 11时10分
 */
public interface RpcResult {

    /**
     * 获取其中存在的数据
     *
     * @return
     */
    Object get();

    /**
     * 设置其中的数据
     *
     * @param obj
     * @return
     */
    Object set(Object obj);
}
