package indi.uhyils.rpc.netty.spi.filter.invoker;

import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exception.RpcBusinessException;
import indi.uhyils.rpc.exception.RpcException;
import indi.uhyils.rpc.exchange.pojo.data.RpcData;
import indi.uhyils.rpc.exchange.pojo.data.factory.NormalRpcResponseFactory;
import indi.uhyils.rpc.exchange.pojo.data.factory.RpcFactoryProducer;
import indi.uhyils.rpc.netty.callback.RpcCallBack;
import indi.uhyils.rpc.netty.pojo.InvokeResult;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.step.RpcStep;
import indi.uhyils.rpc.netty.spi.step.template.ProviderRequestDataExtension;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseByteExtension;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseDataExtension;
import indi.uhyils.rpc.netty.spi.step.template.ProviderResponseExceptionExtension;
import indi.uhyils.rpc.netty.spi.step.template.impl.ProviderResponseExceptionExtensionFactory;
import indi.uhyils.rpc.spi.RpcSpiManager;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2021年01月19日 07时27分
 */
public class LastProviderInvoker implements RpcInvoker {

    /**
     * 回调
     */
    private final RpcCallBack callback;


    /**
     * 生产者接收请求data拦截器
     */
    private List<ProviderRequestDataExtension> providerRequestDataFilters;

    /**
     * 生产者接收请求处理完成后的data拦截器
     */
    private List<ProviderResponseDataExtension> providerResponseDataFilters;

    /**
     * 生产者接收请求处理完成后byte拦截器
     */
    private List<ProviderResponseByteExtension> providerResponseByteFilters;

    /**
     * 生产者接收请求处理业务异常拦截器
     */
    private ProviderResponseExceptionExtension providerResponseExceptionFilters;

    private NormalRpcResponseFactory rpcResponseFactory;

    public LastProviderInvoker(RpcCallBack callback) throws InterruptedException {
        this.callback = callback;

        providerRequestDataFilters = RpcSpiManager.createOrGetExtensionListByClassNoInit(RpcStep.class, ProviderRequestDataExtension.class);
        providerResponseDataFilters = RpcSpiManager.createOrGetExtensionListByClassNoInit(RpcStep.class, ProviderResponseDataExtension.class);
        providerResponseByteFilters = RpcSpiManager.createOrGetExtensionListByClassNoInit(RpcStep.class, ProviderResponseByteExtension.class);
        providerResponseExceptionFilters = ProviderResponseExceptionExtensionFactory.findResponseExceptionExtension();
        rpcResponseFactory = (NormalRpcResponseFactory) RpcFactoryProducer.build(RpcTypeEnum.RESPONSE);
    }

    @Override
    public RpcData invoke(FilterContext context) throws InterruptedException {

        RpcData rpcData = context.getRequestData();
        try {
            // ProviderRequestDataFilter
            for (ProviderRequestDataExtension filter : providerRequestDataFilters) {
                rpcData = filter.doFilter(rpcData);
            }
            RpcData assembly = doInvoke(rpcData);
            // ProviderResponseDataFilter
            for (ProviderResponseDataExtension filter : providerResponseDataFilters) {
                assembly = filter.doFilter(assembly);
            }
            byte[] result = assembly.toBytes();
            for (ProviderResponseByteExtension providerResponseByteFilter : providerResponseByteFilters) {
                result = providerResponseByteFilter.doFilter(result);
            }
            return rpcResponseFactory.createByBytes(result);
        } catch (RpcBusinessException e) {
            return providerResponseExceptionFilters.onBusinessException(rpcData, e);
        } catch (RpcException e) {
            return providerResponseExceptionFilters.onRpcException(rpcData, e);
        } catch (Throwable e) {
            return providerResponseExceptionFilters.onThrowable(rpcData, e);
        }
    }


    /**
     * 执行业务
     *
     * @param rpcData
     *
     * @return
     */
    private RpcData doInvoke(RpcData rpcData) throws InterruptedException {
        InvokeResult invoke = callback.invoke(rpcData.content());
        return callback.assembly(rpcData.unique(), invoke);
    }

}
