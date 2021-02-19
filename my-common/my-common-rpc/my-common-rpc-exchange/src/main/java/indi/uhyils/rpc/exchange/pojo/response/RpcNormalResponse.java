package indi.uhyils.rpc.exchange.pojo.response;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.RpcContent;
import indi.uhyils.rpc.exchange.pojo.RpcHeader;

/**
 * rpc正常响应
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 13时34分
 */
public class RpcNormalResponse extends AbstractRpcResponse {

    public RpcNormalResponse(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
    }

    public RpcNormalResponse() {
    }

    @Override
    protected void initContent() throws RpcException {
        this.content = RpcResponseContentFactory.createByContentArray(this, this.contentArray);
    }

    @Override
    public Integer rpcVersion() {
        return this.version;
    }

    @Override
    public Integer type() {
        return RpcTypeEnum.RESPONSE.getCode();
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
    public String contentString() {
        StringBuilder sb = new StringBuilder();
        for (RpcHeader rpcHeader : rpcHeaders()) {
            sb.append("\n");
            sb.append(String.format("%s:%s", rpcHeader.getName(), rpcHeader.getValue()));
        }
        sb.append("\n");
        sb.append("\n");
        RpcNormalResponseContent content = (RpcNormalResponseContent) content();
        sb.append(content.type());
        sb.append("\n");
        sb.append(content.getResponseContent());
        sb.append("\n");
        return sb.toString();
    }

}
