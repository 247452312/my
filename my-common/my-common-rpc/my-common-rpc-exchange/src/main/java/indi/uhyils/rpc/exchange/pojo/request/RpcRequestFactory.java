package indi.uhyils.rpc.exchange.pojo.request;

import com.alibaba.fastjson.JSON;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcStatusEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.content.MyRpcContent;
import indi.uhyils.rpc.exchange.pojo.*;
import indi.uhyils.rpc.exchange.pojo.response.RpcNormalResponse;
import indi.uhyils.rpc.exchange.pojo.response.RpcResponseContentFactory;
import indi.uhyils.util.LogUtil;

import java.nio.charset.StandardCharsets;

/**
 * 组装请求工厂
 *
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
    public RpcData createByInfo(Long unique, Object[] others, RpcHeader[] rpcHeaders, String... contentArray) throws RpcException, ClassNotFoundException {
        RpcNormalRequest rpcNormalRequest = new RpcNormalRequest();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(rpcHeaders);
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.NULL.getCode());
        rpcNormalRequest.setUnique(unique);
        RpcContent content = RpcRequestContentFactory.createByContentArray(rpcNormalRequest, contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }

    /**
     * 客户端超时
     *
     * @param request 请求
     * @return
     */
    @Override
    public RpcData createTimeoutResponse(RpcData request, Long timeout) throws RpcException {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(request.rpcHeaders());
        String[] contentArray = {String.valueOf(RpcResponseTypeEnum.EXCEPTION.getCode()), "消费者超时:" + timeout};
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.CONSUMER_TIMEOUT.getCode());
        rpcNormalRequest.setUnique(request.unique());
        RpcContent content = RpcResponseContentFactory.createByContentArray(rpcNormalRequest, contentArray);
        rpcNormalRequest.setContent(content);
        rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        return rpcNormalRequest;
    }

    public RpcData createRetriesError(RpcData request, Throwable th) {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(request.rpcHeaders());
        String[] contentArray = {String.valueOf(RpcResponseTypeEnum.EXCEPTION.getCode()), "错误: " + th.getMessage()};
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.BAD_REQUEST.getCode());
        rpcNormalRequest.setUnique(request.unique());
        try {
            RpcContent content = RpcResponseContentFactory.createByContentArray(rpcNormalRequest, contentArray);
            rpcNormalRequest.setContent(content);
            rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        } catch (RpcException e) {
            LogUtil.error(this, e);
        }
        return rpcNormalRequest;
    }

    public RpcData createFallback(RpcData request, Object response) {
        RpcNormalResponse rpcNormalRequest = new RpcNormalResponse();
        rpcNormalRequest.setType(RpcTypeEnum.REQUEST.getCode());
        rpcNormalRequest.setVersion(MyRpcContent.VERSION);
        rpcNormalRequest.setHeaders(request.rpcHeaders());
        String[] contentArray = {String.valueOf(RpcResponseTypeEnum.STRING_BACK.getCode()), JSON.toJSONString(response)};
        rpcNormalRequest.setContentArray(contentArray);
        rpcNormalRequest.setStatus(RpcStatusEnum.CONSUMER_FUSING.getCode());
        rpcNormalRequest.setUnique(request.unique());
        try {
            RpcContent content = RpcResponseContentFactory.createByContentArray(rpcNormalRequest, contentArray);
            rpcNormalRequest.setContent(content);
            rpcNormalRequest.setSize(content.toString().getBytes(StandardCharsets.UTF_8).length);
        } catch (RpcException e) {
            LogUtil.error(this, e);
        }
        return rpcNormalRequest;
    }

    @Override
    protected RpcTypeEnum getRpcType() {
        return RpcTypeEnum.REQUEST;
    }


}
