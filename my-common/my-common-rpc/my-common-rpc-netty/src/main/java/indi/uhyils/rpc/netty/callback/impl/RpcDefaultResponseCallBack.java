package indi.uhyils.rpc.netty.callback.impl;

import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.RpcFactory;
import indi.uhyils.rpc.netty.pojo.RpcFactoryProducer;
import indi.uhyils.rpc.netty.pojo.response.RpcResponseContent;

/**
 * 这个只是一个样例,具体如何执行要看下一级
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月23日 19时15分
 */
public class RpcDefaultResponseCallBack implements RpcCallBack {
    @Override
    public RpcContent getContent(byte[] data) throws RpcException, ClassNotFoundException {
        /*解析*/
        RpcFactory build = RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
        // 获取到的Request
        assert build != null;
        RpcData request = build.createByBytes(data);
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
    public RpcData assembly(String result) {
        return null;
    }


}
