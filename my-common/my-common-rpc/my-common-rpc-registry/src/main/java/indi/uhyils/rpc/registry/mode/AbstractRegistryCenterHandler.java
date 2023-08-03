package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.pojo.RegistryModelInfo;
import java.util.List;

/**
 * 是{@link RegistryCenterHandler}的推荐实现,可以在此基础上小部分的调整具体逻辑
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2023年03月30日 17时05分
 */
public abstract class AbstractRegistryCenterHandler implements RegistryCenterHandler {


    /**
     * 监听的对应服务的service
     */
    protected Class<?> serviceClass;

    /**
     * 缓存的注册中心信息,需要注意的是 无论是消费者还是生产者,这里的注册中心信息都是对应生产者的信息
     */
    protected List<RegistryModelInfo> registryModelInfo;

    @Override
    public void init(Object... params) throws InterruptedException {
        // 初始化要求有监听的服务
        this.serviceClass = (Class<?>) params[0];
        otherDoInit();
    }

    @Override
    public List<RegistryModelInfo> cacheInfo() {
        return registryModelInfo;
    }

    /**
     * 子类进行初始化
     */
    protected abstract void otherDoInit();

    /**
     * 获取自身在指定注册中心内的信息
     *
     * @return
     */
    protected abstract void initRegistryInfo();

}
