package indi.uhyils.rpc.exchange.pojo.content;

import indi.uhyils.rpc.exchange.enums.RpcResponseContentEnum;
import indi.uhyils.rpc.exchange.pojo.content.impl.RpcResponseContentImpl;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.util.CollectionUtil;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * rpc响应内容工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 14时09分
 */
public class RpcResponseContentFactory {

    /**
     * 内容大小
     */
    private static final Integer CONTENT_SIZE = 2;

    /**
     * 根据content列表来构建一个返回体
     *
     * @param rpcData
     * @param contentArray
     *
     * @return
     */
    public static RpcContent createByContentArray(RpcData rpcData, String[] contentArray) {
        if (contentArray.length != CONTENT_SIZE) {
            String[] strings = CollectionUtil.arrayCopy(contentArray, 0, CONTENT_SIZE);
            strings[CONTENT_SIZE - 1] = Arrays.stream(contentArray).skip(CONTENT_SIZE - 1).collect(Collectors.joining("\n"));
            contentArray = strings;
        }
        RpcResponseContentImpl content = new RpcResponseContentImpl(rpcData, contentArray);
        int type = Integer.parseInt(contentArray[RpcResponseContentEnum.TYPE.getLine()]);
        content.setResponseType(type);
        content.setResponseContent(contentArray[RpcResponseContentEnum.RESPONSE_CONTENT.getLine()]);
        return content;
    }
}
