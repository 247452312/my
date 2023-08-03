package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.pojo.RegistryModelInfo;
import indi.uhyils.rpc.spi.RpcSpiExtension;
import java.util.List;

/**
 * 注册中心句柄 是服务级别为单位进行注册与缓存的
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月30日 17时03分
 */
public interface RegistryCenterHandler extends RpcSpiExtension {

    /**
     * 获取接口对应的缓存的信息
     * 此信息有多种情况
     * 1.只有一个集群且此集群只有一台机器 即单机提供服务 此时返回的list.size==1
     * 2.只有一个集群且此集群有多台机器 即集群提供服务 此时返回的list.size==机器数量
     * 3.有多个集群且每个集群有多台机器 即多集群同服务 此时返回的list.size==总提供服务的机器数量
     * <p>
     * 前面两个情况比较常用,所以好理解 第三种情况举例为:
     * 现在要有一个SwaggerService 来对本应用的rpc接口提供文档服务, 但是User服务 log服务等多个服务均引入的rpcSwagger包 此时多集群都会有此服务,所以需要考虑第三种情况
     *
     * @return
     */
    List<RegistryModelInfo> cacheInfo();

    /**
     * 关闭连接
     * 消费者只需要关闭,生产者需要注销自己在注册中心的信息
     */
    void close();

}
