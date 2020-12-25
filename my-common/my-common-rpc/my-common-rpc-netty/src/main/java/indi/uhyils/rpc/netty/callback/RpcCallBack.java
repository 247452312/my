package indi.uhyils.rpc.netty.callback;

import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 18时55分
 */
public interface RpcCallBack {


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
     * @param result
     * @return
     */
    RpcData assembly(String result) throws RpcException, ClassNotFoundException;

}
