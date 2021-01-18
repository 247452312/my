package indi.uhyils.rpc.pojo.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.rpc.exception.ContentArrayQuantityMismatchException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.pojo.RpcContent;
import indi.uhyils.rpc.pojo.RpcData;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 14时09分
 */
public class RpcRequestContentFactory {

    /**
     * 内容大小
     */
    private static final Integer CONTENT_MIN_SIZE = 5;

    public static RpcContent createByContentArray(RpcData rpcData, String[] contentArray) throws ClassNotFoundException, RpcException {
        if (contentArray.length < CONTENT_MIN_SIZE) {
            throw new ContentArrayQuantityMismatchException(contentArray.length, CONTENT_MIN_SIZE);
        }
        RpcNormalRequestContent content = new RpcNormalRequestContent(rpcData);
        content.setServiceName(contentArray[0]);
        content.setServiceVersion(contentArray[1]);
        content.setMethodName(contentArray[2]);
        String[] methodParamterTypes = contentArray[3].split(";");
        content.setMethodParamterTypes(methodParamterTypes);
        JSONArray argsMap = JSON.parseArray(contentArray[4]);
        List<Object> args = new ArrayList<>();
        for (int i = 0; i < methodParamterTypes.length; i++) {
            String classPath = methodParamterTypes[i];
            if (StringUtils.isEmpty(classPath)) {
                continue;
            }
            Class<?> clazz = Class.forName(classPath);
            Object o = JSON.parseObject(argsMap.get(i).toString(), clazz);
            args.add(o);
        }
        content.setArgs(args.toArray());

        List<Object> others = new ArrayList<>();
        for (int i = 5; i < contentArray.length; i++) {
            String s = contentArray[i];
            Object parse = JSON.parse(s);
            others.add(parse);
        }
        content.setOthers(others.toArray());
        return content;
    }
}
