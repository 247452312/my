package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.cluster.Cluster;
import indi.uhyils.rpc.netty.enums.RpcNettyTypeEnum;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;
import indi.uhyils.rpc.spi.RpcSpiExtension;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 16时50分
 */
public interface RegistryMode extends RpcSpiExtension {

    /**
     * 获取配置
     *
     * @param interfaceName
     *
     * @return
     */
    String getConfig(String interfaceName);

    /**
     * 发布配置或修改
     *
     * @param interfaceName
     * @param content
     *
     * @return
     */
    Boolean publishConfig(String interfaceName, String content);

    /**
     * 删除配置
     *
     * @param interfaceName
     *
     * @return
     */
    Boolean removeConfig(String interfaceName);


    /**
     * 添加配置监听
     *
     * @param interfaceName
     * @param listener
     */
    void addConfigListener(String interfaceName, RegistryServiceListener listener);

    /**
     * 删除配置监听
     *
     * @param interfaceName
     * @param listener
     */
    void removeConfigListener(String interfaceName, RegistryServiceListener listener);

    /**
     * 获取目标接口的信息(无配置)
     *
     * @param interfaceName
     *
     * @return
     */
    List<RegistryInfo> getTargetInterfaceInfo(String interfaceName);

    /**
     * 服务端注册
     *
     * @param info
     *
     * @return
     */
    Boolean registry(RegistryInfo info);

    /**
     * 服务端开放服务
     *
     * @return
     */
    Boolean allowToPublish();

    /**
     * 服务端关闭服务
     *
     * @return
     */
    Boolean notAllowToPublish();

    /**
     * 服务端注销
     *
     * @return
     */
    void removeInstance();

    /**
     * 添加服务监听
     *
     * @param interfaceName
     * @param listener
     */
    void addServiceListener(String interfaceName, String groupName, RegistryServiceListener listener);

    /**
     * 删除服务监听
     *
     * @param interfaceName
     * @param listener
     */
    void removeServiceListener(String interfaceName, RegistryServiceListener listener);

    /**
     * 设置此nacos连接的类型
     *
     * @param type
     */
    void setType(RpcNettyTypeEnum type);

    /**
     * 创建一个新的注册中心监听器
     *
     * @param interfaceName 服务名称
     * @param cluster       服务集群
     */
    void createListener(String interfaceName, Cluster cluster);

    /**
     * 是否正在对外提供服务
     *
     * @return
     */
    Boolean isPublish();
}
