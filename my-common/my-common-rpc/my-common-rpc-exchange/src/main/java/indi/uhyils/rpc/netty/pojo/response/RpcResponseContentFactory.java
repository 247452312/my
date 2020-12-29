package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.netty.exception.ContentArrayQuantityMismatchException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcData;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 14时09分
 */
public class RpcResponseContentFactory {

    /**
     * 内容大小
     */
    private static final Integer CONTENT_SIZE = 2;

    public static RpcContent createByContentArray(RpcData rpcData, String[] contentArray) throws RpcException {
        if (contentArray.length != CONTENT_SIZE) {
            throw new ContentArrayQuantityMismatchException(contentArray.length, CONTENT_SIZE);
        }
        RpcNormalResponseContent content = new RpcNormalResponseContent(rpcData);
        int type = Integer.parseInt(contentArray[0]);
        content.setResponseType(type);
        content.setResponseContent(contentArray[1]);
        return content;
    }
}
