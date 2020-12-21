package indi.uhyils.rpc.netty.pojo;

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
        return this.rpcData.getContentArray()[line];
    }

    @Override
    public RpcData getRpcData() {
        return rpcData;
    }

    protected void setRpcData(RpcData rpcData) {
        this.rpcData = rpcData;
    }
}
