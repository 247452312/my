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
     * @param data
     * @return
     */
    RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException;

}
