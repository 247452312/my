package indi.uhyils.rpc.netty.callback.impl;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcContent;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.RpcFactory;
import indi.uhyils.rpc.exchange.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.response.RpcResponseContent;

/**
 * 这个只是一个样例,具体如何执行要看下一级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 19时15分
 */
public class RpcDefaultResponseCallBack implements RpcCallBack {
    @Override
    public RpcData getRpcData(byte[] data) throws RpcException, ClassNotFoundException {
        /*解析*/
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
        // 获取到的Request
        assert build != null;
        return build.createByBytes(data);
    }

    @Override
    public RpcContent getContent(byte[] data) throws RpcException, ClassNotFoundException {
        RpcData request = getRpcData(data);
        return request.content();
    }

    @Override
    public String invoke(RpcContent content) {
        if (content instanceof RpcResponseContent) {
            RpcResponseContent requestContent = (RpcResponseContent) content;
            return requestContent.getResponseContent();
        }
        return null;
    }

    @Override
    public RpcData assembly(Long unique, String result) {
        return null;
    }


}
