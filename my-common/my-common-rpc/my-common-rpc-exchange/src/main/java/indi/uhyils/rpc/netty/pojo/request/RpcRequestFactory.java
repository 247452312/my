package indi.uhyils.rpc.netty.pojo.request;

import indi.uhyils.rpc.netty.content.MyRpcContent;
import indi.uhyils.rpc.netty.enums.RpcStatusEnum;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.*;

import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时38分
 */
public class RpcRequestFactory extends AbstractRpcFactory {

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
    public RpcData createByInfo( Object[] others, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException, ClassNotFoundException {
        RpcNormalRequest rpcNormalRequest = new RpcNormalRequest();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(rpcHeaders);
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.NULL.getCode());
        rpcNormalRequest.setUnique(9L);
        RpcContent content = RpcRequestContentFactory.createByContentArray(rpcNormalRequest, contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }

    @Override
    protected RpcTypeEnum getRpcType() {
        return RpcTypeEnum.REQUEST;
    }


}
