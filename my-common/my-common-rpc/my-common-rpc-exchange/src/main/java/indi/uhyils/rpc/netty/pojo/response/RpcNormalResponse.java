package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.exception.RpcException;
import indi.uhyils.rpc.netty.pojo.RpcContent;
import indi.uhyils.rpc.netty.pojo.RpcHeader;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 13时34分
 */
public class RpcNormalResponse extends RpcResponse {

    public RpcNormalResponse(byte[] data) throws RpcException, ClassNotFoundException {
        super(data);
    }

    public RpcNormalResponse() {
    }

    @Override
    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    protected void initContent() throws RpcException {
        this.content = RpcResponseContentFactory.createByContentArray(this.contentArray);
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
        RpcNormalResponseContent content = (RpcNormalResponseContent) content();
        sb.append(content.type());
        sb.append("\n");
        sb.append(content.getResponseContent());
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public void initSize(byte[] data) {
        this.size = (data[4] << 24) + (data[5] << 16) + (data[6] << 8) + data[7];
    }
}
