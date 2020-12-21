package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcHeader;

import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时47分
 */
public class RpcResponseFactory implements RpcFactory {
    public volatile static RpcFactory instance;

    public static RpcFactory getInstance() {
        if (null == instance) {
            synchronized (RpcResponseFactory.class) {
                if (null == instance) {
                    instance = new RpcResponseFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public RpcData createByBytes(byte[] data) throws RpcException, ClassNotFoundException {
        return new RpcNormalResponse(data);
    }

    @Override
    public RpcData createByInfo(Integer rpcVersion, Object[] others, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(rpcVersion);
        rpcNormalRequest.setHeaders(rpcHeaders);
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus((Byte) others[0]);

        RpcContent content = RpcResponseContentFactory.createByContentArray(contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }
}
