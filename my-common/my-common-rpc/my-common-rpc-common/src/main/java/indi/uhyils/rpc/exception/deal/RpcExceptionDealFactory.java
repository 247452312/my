package indi.uhyils.rpc.exception.deal;

import indi.uhyils.rpc.config.RpcConfigFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年07月01日 09时55分
 */
public final class RpcExceptionDealFactory {

    /**
     * 默认的exceptionDealHandler
     */
    private static final String DEFAULT_EXCEPTION_DEAL = "defaultRpcExceptionHandler";

    /**
     * 配置中exceptionDealHandler
     */
    private static final String EXCEPTION_DEAL_SPI_NAME = "exceptionDeal";

    /**
     * 获取rpcException处理器
     *
     * @return
     */
    public static RpcExceptionDealHandler find() throws InterruptedException {
        // spi 获取消费者的注册者信息
        String exceptionDealName = (String) RpcConfigFactory.getCustomOrDefault(EXCEPTION_DEAL_SPI_NAME, DEFAULT_EXCEPTION_DEAL);
        // 返回一个构造完成的消费者
        return (RpcExceptionDealHandler) RpcSpiManager.createOrGetExtensionByClass(RpcExceptionDealHandler.class, exceptionDealName);
    }
}
