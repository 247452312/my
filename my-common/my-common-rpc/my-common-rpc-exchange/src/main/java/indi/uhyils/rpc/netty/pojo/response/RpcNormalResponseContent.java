package indi.uhyils.rpc.netty.pojo.response;

import indi.uhyils.rpc.netty.enums.RpcTypeEnum;
import indi.uhyils.rpc.netty.pojo.RpcData;
import indi.uhyils.rpc.netty.pojo.AbstractRpcObserverAdapter;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 11时03分
 */
public class RpcNormalResponseContent extends AbstractRpcObserverAdapter implements RpcResponseContent {

    /**
     * 响应值类型
     */
    private Integer responseType;

    /**
     * 响应值体
     */
    private String responseContent;

    public RpcNormalResponseContent(RpcData rpcData) {
        setRpcData(rpcData);
    }

    @Override
    public Integer type() {
        return RpcTypeEnum.REQUEST.getCode();
    }

    @Override
    public String execute() {
        throw new RuntimeException("response不能进行执行操作");
    }


    @Override
    public Integer responseType() {
        return responseType;
    }

    @Override
    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public void setResponseType(Integer responseType) {
        this.responseType = responseType;
    }
}
