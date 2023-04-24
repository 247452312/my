package indi.uhyils.rpc.registry.mode;

import indi.uhyils.rpc.registry.pojo.event.RegistryEvent;
import java.util.function.Consumer;

/**
 *
 * 消费者 注册中心句柄
 *
 * @date 文件创建日期 2023年04月23日 15时19分
 * @author uhyils <247452312@qq.com>
 */
public interface ConsumerRegistryCenterHandler extends RegistryCenterHandler {


    /**
     * 注册事件回调
     *
     * @param function
     */
    void registerEvent(Consumer<RegistryEvent> function);

}
