package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcHeader;

import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时38分
 */
public class RpcRequestFactory implements RpcFactory {

    public volatile static RpcFactory instance;

    private RpcRequestFactory() {
    }

    public static RpcFactory getInstance() {
        if (null == instance) {
            synchronized (RpcRequestFactory.class) {
                if (null == instance) {
                    instance = new RpcRequestFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException {
        return new RpcNormalRequest(data);
    }

    @Override
    public RpcData createByInfo(Integer rpcVersion, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException, ClassNotFoundException {
        RpcNormalRequest rpcNormalRequest = new RpcNormalRequest();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(rpcVersion);
        rpcNormalRequest.setHeaders(rpcHeaders);
        rpcNormalRequest.setContentArray(contentArray);
        RpcContent content = RpcRequestContentFactory.createByContentArray(contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }
}
