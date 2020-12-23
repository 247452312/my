package indi.uhyils.rpc.netty.callback;

import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 18时55分
 */
public interface RpcRequestCallback {

    /**
     * 执行方法,下一级去实现此方法
     *
     * @param content
     * @return
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    RpcData invoke(RpcContent content) throws RpcException, ClassNotFoundException;

}
