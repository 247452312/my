package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.pojo.info.RegistryInfo;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年12月26日 16时50分
 */
public interface RegistryUtilInterface {

    /**
     * 获取目标接口的信息
     *
     * @param serviceName
     * @param interfaceName
     * @return
     */
    RegistryInfo getTargetInterfaceInfo(String serviceName, String interfaceName);

    /**
     * 注册
     *
     * @param info
     * @return
     */
    Boolean registry(RegistryInfo info);
}
