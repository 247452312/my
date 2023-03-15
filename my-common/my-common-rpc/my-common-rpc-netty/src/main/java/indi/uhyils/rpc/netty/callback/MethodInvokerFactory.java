package indi.uhyils.rpc.netty.callback;

import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月20日 09时55分
 */
public class MethodInvokerFactory {

    /**
     * 默认的方法执行器
     */
    private static final String DEFAULT_METHOD_INVOKER = "defaultMethodInvoker";


    /**
     * 配置中的方法执行器
     */
    private static final String METHOD_INVOKER_SPI_NAME = "methodInvokerSpi";


    /**
     * 创建一个方法执行器
     *
     * @return
     */
    public static MethodInvoker createMethodInvoker() throws InterruptedException {
        // spi 获取消费者的注册者信息
        String methodInvokerName = (String) RpcConfigFactory.getCustomOrDefault(METHOD_INVOKER_SPI_NAME, DEFAULT_METHOD_INVOKER);
        // 返回一个构造完成的方法执行器
        return (MethodInvoker) RpcSpiManager.createOrGetExtensionByClass(MethodInvoker.class, methodInvokerName);
    }
}
