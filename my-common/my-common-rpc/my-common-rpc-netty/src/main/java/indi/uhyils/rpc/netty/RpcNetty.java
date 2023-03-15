package indi.uhyils.rpc.netty;

import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 * rpc调用中的netty
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月20日 13时42分
 */
public interface RpcNetty extends RpcSpiExtension {


    /**
     * 关闭,不会立即关闭.会等待线程结束
     *
     * @return
     */
    Boolean shutdown();

    /**
     * 发送信息 等待回应
     *
     * @param rpcData
     *
     * @return
     */
    RpcData sendMsg(RpcData rpcData) throws InterruptedException;

}
