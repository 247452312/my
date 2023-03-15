package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 * 这里的T指service
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月27日 15时30分
 */
public interface Registry<T> extends RpcSpiExtension {

    /**
     * 远程调用
     *
     * @param rpcData rpc
     *
     * @return 返回值的json串, 或者报错时有关报错的信息,这里除了被打断的异常之外,不可能抛出其他的异常,扩展开发时需要将其余的异常封装为正常的返回
     *
     * @throws InterruptedException 过程调用被打断错误(例如超时)
     */
    RpcData invoke(RpcData rpcData) throws InterruptedException;

    /**
     * 关闭服务
     */
    void close();


}
