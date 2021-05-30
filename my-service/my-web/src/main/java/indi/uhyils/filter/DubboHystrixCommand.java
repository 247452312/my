package indi.uhyils.filter;

import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.rpc.enums.RpcResponseTypeEnum;
import indi.uhyils.rpc.enums.RpcTypeEnum;
import indi.uhyils.rpc.exchange.enum_.RpcRequestContentEnum;
import indi.uhyils.rpc.exchange.pojo.RpcData;
import indi.uhyils.rpc.exchange.pojo.factory.RpcFactoryProducer;
import indi.uhyils.rpc.exchange.pojo.demo.factory.NormalRpcRequestFactory;
import indi.uhyils.rpc.exchange.pojo.response.content.RpcResponseContent;
import indi.uhyils.rpc.netty.spi.filter.FilterContext;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcInvoker;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcResult;
import indi.uhyils.rpc.netty.spi.filter.invoker.RpcResultImpl;


/**
 * dubbo熔断器配置类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月19日 15时43分
 */
public class DubboHystrixCommand extends HystrixCommand<RpcResult> {


    /**
     * 默认线程池大小 16
     */
    private static final int DEFAULT_THREADPOOL_CORE_SIZE = 1 << 4;
    /**
     * 10秒内失败阈值
     */
    private static final int FAIL_THRESHOLD_PER_10_SECOND = 20;

    /**
     * 熔断后需要经过一段冷却时间后放行部分请求再次进行尝试,若依旧不行,则开启熔断保护 此属性为熔断后重试的时间间隔
     */
    private static final int RETRY_SECOND = 30;

    /**
     * 错误率, 如果达到此错误率则开启熔断保护
     */
    private static final int ERROR_RATE = 50;

    private final RpcInvoker invoker;
    private final FilterContext invocation;

    public DubboHystrixCommand(RpcInvoker invoker, FilterContext invocation) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invocation.getRpcResult().get().content().getLine(RpcRequestContentEnum.SERVICE_NAME.getLine())))
                .andCommandKey(HystrixCommandKey.Factory.asKey(String.format("%s_%d", invocation.getRpcResult().get().content().getLine(RpcRequestContentEnum.METHOD_NAME.getLine()),
                        invocation.getRpcResult().get().content().getLine(RpcRequestContentEnum.METHOD_PARAM_TYPE.getLine()).split("\n").length)))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //10秒钟内至少19此请求失败，熔断器才发挥起作用
                        .withCircuitBreakerRequestVolumeThreshold(FAIL_THRESHOLD_PER_10_SECOND)
                        //熔断器中断请求30秒后会进入半打开状态,放部分流量过去重试
                        .withCircuitBreakerSleepWindowInMilliseconds(RETRY_SECOND * 1000)
                        //错误率达到50开启熔断保护
                        .withCircuitBreakerErrorThresholdPercentage(ERROR_RATE)
                        //使用dubbo的超时，禁用这里的超时
                        .withExecutionTimeoutEnabled(false))
                //获取线程池大小
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(8)));
        this.invoker = invoker;
        this.invocation = invocation;
    }


    @Override
    protected RpcResult getFallback() {
        NormalRpcRequestFactory build = (NormalRpcRequestFactory) RpcFactoryProducer.build(RpcTypeEnum.REQUEST);
        ServiceResult sr = ServiceResult.buildFailedResult("您好,您请求的服务暂时不可用,请一分钟后重试!", null, null);
        RpcData rpcData = build.createFallback(invocation.getRpcResult().get(), sr);
        try {
            RpcResultImpl rpcResult = new RpcResultImpl();
            rpcResult.set(rpcData);
            return rpcResult;
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @Override
    protected RpcResult run() throws Exception {
        RpcResult result = invoker.invoke(invocation);
        RpcData rpcData = result.get();
        RpcResponseContent content = (RpcResponseContent) rpcData.content();
        RpcResponseTypeEnum parse = RpcResponseTypeEnum.parse(content.responseType());
        // 如果远程调用异常，抛出异常就会调用getFallback()方法去执行降级逻辑
        if (parse == RpcResponseTypeEnum.EXCEPTION) {
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION,
                    DubboHystrixCommand.class, content.getResponseContent(),
                    null, null);
        }

        return result;
    }
}
