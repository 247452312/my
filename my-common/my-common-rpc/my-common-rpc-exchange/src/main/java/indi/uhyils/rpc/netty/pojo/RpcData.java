package indi.uhyils.rpc.netty.pojo;

/**
 * rpc数据体
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月18日 10时14分
 */
public interface RpcData {

    /**
     * RPC版本
     *
     * @return
     */
    Integer rpcVersion();

    /**
     * RPC类型,0->请求 1->响应
     *
     * @return
     */
    Integer type();

    /**
     * RPC内容的size,最大值{@link Integer#MAX_VALUE}
     *
     * @return
     */
    Integer size();

    /**
     * 获取RPC中的header
     *
     * @return
     */
    RpcHeader[] rpcHeaders();

    /**
     * 内容
     *
     * @return
     */
    RpcContent content();

    /**
     * 获取rpc全部
     *
     * @return
     */
    byte[] toBytes();

    /**
     * 获取content部分的字符串
     *
     * @return
     */
    String getContentString();

    /**
     * 获取类型
     *
     * @param data
     */
    void initSize(byte[] data);


    /**
     * 处理其他事情
     *
     * @param o
     */
    void dealOtherThing(Object o);

}
