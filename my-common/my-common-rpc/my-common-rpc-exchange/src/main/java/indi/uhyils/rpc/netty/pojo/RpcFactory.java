package indi.uhyils.rpc.netty.pojo;

import indi.uhyils.rpc.netty.exception.RpcException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时39分
 */
public interface RpcFactory {

    /**
     * 使用数据流新建一个rpc体
     *
     * @param data 数据流
     * @return 创建之后的pojo
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException;

    /**
     * 根据一些必要的信息创建RPC体
     *
     * @param rpcVersion   rpc版本
     * @param rpcHeaders   rpc头
     * @param others       其他
     * @param contentArray rpc内容体以及其他内容
     * @return
     * @throws RpcException
     * @throws ClassNotFoundException
     */
    RpcData createByInfo(Integer rpcVersion, Object[] others, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException, ClassNotFoundException;

}
