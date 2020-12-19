package indi.uhyils.rpc.netty.pojo.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcHeader;

/**
 * rpc正常请求体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 10时52分
 */
public class RpcNormalRequest extends RpcRequest {

    public RpcNormalRequest(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
    }

    public RpcNormalRequest() {
    }

    @Override
    protected void initContent() throws RpcException, ClassNotFoundException {
        this.content = RpcRequestContentFactory.createByContentArray(this.contentArray);
    }


    @Override
    public Integer rpcVersion() {
        return this.version;
    }

    @Override
    public Integer type() {
        return this.type;
    }

    @Override
    public Integer size() {
        return this.size;
    }

    @Override
    public RpcHeader[] rpcHeaders() {
        return this.headers;
    }

    @Override
    public RpcContent content() {
        return this.content;
    }


    @Override
    public String getContentString() {
        StringBuilder sb = new StringBuilder();
        for (RpcHeader rpcHeader : rpcHeaders()) {
            sb.append("\n");
            sb.append(String.format("%s:%s", rpcHeader.getName(), rpcHeader.getValue()));
        }
        sb.append("\n");
        sb.append("\n");
        RpcNormalRequestContent content = (RpcNormalRequestContent) content();
        sb.append(content.getServiceName());
        sb.append("\n");
        sb.append(content.getServiceVersion());
        sb.append("\n");
        sb.append(content.getMethodName());
        sb.append("\n");
        String[] methodParameterTypes = content.getMethodParameterTypes();
        for (String methodParameterType : methodParameterTypes) {
            sb.append(methodParameterType);
            sb.append(";");
        }
        sb.delete(sb.length() - 1, sb.length());
        sb.append("\n");
        sb.append(JSONArray.toJSONString(content.getArgs()));
        sb.append("\n");
        Object[] others = content.getOthers();
        for (Object other : others) {
            sb.append(JSON.toJSONString(other));
            sb.append("\n");
        }
        return sb.toString();
    }


}
