package indi.uhyils.rpc.netty.spi.step.template.impl;

import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseExceptionExtension;
import indi.uhyils.rpc.spi.RpcSpiManager;

/**
 * 异常处理扩展工厂
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月05日 17时42分
 */
public class ProviderResponseExceptionExtensionFactory {


    /**
     * 默认的registry
     */
    private static final String DEFAULT_EXCEPTION_EXTENSION = "default_exception_extension";

    /**
     * 配置中registry
     */
    private static final String EXCEPTION_EXTENSION_SPI_NAME = "exceptionExtensionSpi";

    public static ProviderResponseExceptionExtension findResponseExceptionExtension() throws InterruptedException {
        // spi 获取名称
        String exceptionExtensionName = (String) RpcConfigFactory.getCustomOrDefault(EXCEPTION_EXTENSION_SPI_NAME, DEFAULT_EXCEPTION_EXTENSION);
        // 返回一个构造完成的异常处理扩展
        return (ProviderResponseExceptionExtension) RpcSpiManager.createOrGetExtensionByClass(ProviderResponseExceptionExtension.class, exceptionExtensionName);
    }

}
