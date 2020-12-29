package indi.uhyils.rpc.netty.exception;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;

import java.util.Objects;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月22日 10时19分
 */
public class RpcTypeNotSupportedException extends RpcException {

    public RpcTypeNotSupportedException(Integer type, Integer shouldType) {
        super("rpc类型不符合,应该是: " + (Objects.equals(type, RpcTypeEnum.RESPONSE.getCode()) ? "响应" : "请求") + "实际是: " + (Objects.equals(shouldType, RpcTypeEnum.RESPONSE.getCode()) ? "响应" : "请求"));
    }
}
