package indi.uhyils.rpc.pojo.response;

import indi.uhyils.rpc.content.MyRpcContent;
import indi.uhyils.rpc.enums.RpcStatusEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.pojo.*;

import java.nio.charset.StandardCharsets;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 12时47分
 */
public class RpcResponseFactory extends AbstractRpcFactory {

    public volatile static RpcResponseFactory instance;

    public static RpcResponseFactory getInstance() {
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
    public RpcData createByInfo(Long unique, Object[] others, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.RESPONSE.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(rpcHeaders);
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus((Byte) others[0]);
        rpcNormalRequest.setUnique(unique);

        RpcContent content = RpcResponseContentFactory.createByContentArray(rpcNormalRequest, contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }

    public RpcData createErrorResponse(Long unique, Throwable e, RpcHeader[] rpcHeaders) throws RpcException {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.RESPONSE.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(rpcHeaders);
        String[] contentArray = new String[]{e == null ? RpcStatusEnum.PROVIDER_ERROR.getName() : e.getMessage()};
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.PROVIDER_ERROR.getCode());
        rpcNormalRequest.setUnique(unique);

        RpcContent content = RpcResponseContentFactory.createByContentArray(rpcNormalRequest, contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;

    }

    @Override
    protected RpcTypeEnum getRpcType() {
        return RpcTypeEnum.RESPONSE;
    }
}
