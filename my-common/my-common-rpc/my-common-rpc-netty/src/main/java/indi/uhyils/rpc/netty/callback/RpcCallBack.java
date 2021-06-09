package indi.uhyils.rpc.netty.callback;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcContent;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 18时55分
 */
public interface RpcCallBack extends RpcSpiExtension {


    /**
     * 获取rpc数据
     *
     * @param data
     * @return
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    RpcData getRpcData(byte[] data) throws RpcException, ClassNotFoundException;

    /**
     * 获取rpc体
     *
     * @param data
     * @return
     */
    RpcContent getContent(byte[] data) throws RpcException, ClassNotFoundException;

    /**
     * 执行方法,下一级去实现此方法
     *
     * @param content
     * @return
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    String invoke(RpcContent content);


    /**
     * 组装返回值
     *
     * @param unique
     * @param result
     * @return
     */
    RpcData assembly(Long unique, String result) throws RpcException, ClassNotFoundException;

}
