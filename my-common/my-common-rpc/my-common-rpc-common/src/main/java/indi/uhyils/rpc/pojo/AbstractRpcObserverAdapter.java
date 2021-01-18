package indi.uhyils.rpc.pojo;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月21日 21时24分
 */
public abstract class AbstractRpcObserverAdapter implements RpcContent {

    /**
     * rpcData
     */
    private RpcData rpcData;


    @Override
    public String getLine(Integer line) {
        return this.rpcData.contentArray()[line];
    }

    @Override
    public RpcData getRpcData() {
        return rpcData;
    }

    protected void setRpcData(RpcData rpcData) {
        this.rpcData = rpcData;
    }

    @Override
    public String toString() {
        return this.rpcData.contentString();
    }
}
