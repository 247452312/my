package indi.uhyils.rpc.registry;

import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.spi.RpcSpiExtension;

/**
 *
 * 注册层,注册层代表了可以和注册中心交互并实时调整自己的连接
 * 例如consumerRegistry负责消费者去获取生产者地址,集群等信息,那么也可以通过注册中心的消息推送得知生产者上下线,生产者版本号变更等消息,以动态的调用可用的生产者集群
 * providerRegistry 负责在启动时去将自身信息注册到注册中心
 *
 * @date 文件创建日期 2023年04月23日 13时52分
 * @author uhyils <247452312@qq.com>
 */
public interface Registry extends RpcSpiExtension {

    /**
     * 远程调用
     *
     * @param rpcData rpc
     *
     * @return 返回值的json串, 或者报错时有关报错的信息,这里除了被打断的异常之外,不可能抛出其他的异常,扩展开发时需要将其余的异常封装为正常的返回
     *
     * @throws InterruptedException 过程调用被打断错误(例如超时)
     */
    RpcData invoke(RpcData rpcData) throws InterruptedException;

    /**
     * 关闭服务
     */
    Boolean close();
}
