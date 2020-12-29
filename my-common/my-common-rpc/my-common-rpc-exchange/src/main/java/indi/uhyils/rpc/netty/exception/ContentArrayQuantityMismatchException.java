package indi.uhyils.rpc.netty.exception;

import indi.uhyils.rpc.exception.RpcException;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 14时58分
 */
public class ContentArrayQuantityMismatchException extends RpcException {

    public ContentArrayQuantityMismatchException(Integer size, Integer shouldSize) {
        super(String.format("RPC调用内容体数组数量不匹配: 现有数量:%d  应该数量:%d", size, shouldSize));
    }
}
