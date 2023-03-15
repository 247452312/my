package indi.uhyils.rpc.netty.spi.step.template;

import indi.uhyils.rpc.exception.RpcBusinessException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 * 异常的优先级顺序为 RpcBusinessException -> RpcException -> Throwable
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月05日 17时02分
 */
public interface ProviderResponseExceptionExtension extends RpcSpiExtension {

    /**
     * 业务异常时
     *
     * @param rpcData 请求
     * @param e
     *
     * @return
     */
    RpcData onBusinessException(RpcData rpcData, RpcBusinessException e) throws InterruptedException;

    /**
     * rpc异常时
     *
     * @param rpcData 请求
     * @param e
     *
     * @return
     */
    RpcData onRpcException(RpcData rpcData, RpcException e) throws InterruptedException;


    /**
     * throwable时
     *
     * @param rpcData 请求
     * @param th
     *
     * @return
     */
    RpcData onThrowable(RpcData rpcData, Throwable th) throws InterruptedException;
}
