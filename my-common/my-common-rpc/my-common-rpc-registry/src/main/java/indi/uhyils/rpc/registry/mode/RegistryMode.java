package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.exception.RegistryException;
import indi.uhyils.rpc.registry.exception.RegistryTypeException;
import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;

import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 16时50分
 */
public interface RegistryMode {

    /**
     * 获取配置
     *
     * @param interfaceName
     * @return
     */
    String getConfig(String interfaceName) throws Exception;

    /**
     * 发布配置或修改
     *
     * @param interfaceName
     * @param content
     * @return
     */
    Boolean publishConfig(String interfaceName, String content) throws Exception;

    /**
     * 删除配置
     *
     * @param interfaceName
     * @return
     */
    Boolean removeConfig(String interfaceName) throws Exception;


    /**
     * 添加配置监听
     *
     * @param interfaceName
     * @param listener
     */
    void addConfigListener(String interfaceName, RegistryServiceListener listener) throws Exception;

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
     * @param serviceName
     * @param interfaceName
     * @return
     */
    List<RegistryInfo> getTargetInterfaceInfo(String serviceName, String interfaceName) throws Exception;

    /**
     * 服务端注册
     *
     * @param info
     * @return
     * @throws Exception
     * @throws RegistryTypeException
     * @throws RegistryException
     */
    Boolean registry(RegistryInfo info) throws Exception, RegistryTypeException, RegistryException;

    /**
     * 服务端注销
     *
     * @return
     * @throws Exception
     * @throws RegistryTypeException
     * @throws RegistryException
     */
    void removeInstance(String interfaceName, String ip, int port) throws Exception, RegistryTypeException, RegistryException;

    /**
     * 添加服务监听
     *
     * @param interfaceName
     * @param listener
     * @throws Exception
     */
    void addServiceListener(String interfaceName, RegistryServiceListener listener) throws Exception;

    /**
     * 删除服务监听
     *
     * @param interfaceName
     * @param listener
     * @throws Exception
     */
    void removeServiceListener(String interfaceName, RegistryServiceListener listener) throws Exception;
}
