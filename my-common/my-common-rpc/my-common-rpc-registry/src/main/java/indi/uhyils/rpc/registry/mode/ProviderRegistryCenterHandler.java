package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.pojo.RegistryModelInfo;

/**
 * 服务提供者 消费中心句柄
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年04月23日 15时20分
 */
public interface ProviderRegistryCenterHandler extends RegistryCenterHandler {


    /**
     * 服务端注册
     *
     * @param info
     *
     * @return
     */
    Boolean registry(RegistryModelInfo info);

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
     * 是否正在对外提供服务
     *
     * @return
     */
    Boolean isPublish();

}
