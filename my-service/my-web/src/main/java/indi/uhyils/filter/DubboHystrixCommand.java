package indi.uhyils.filter;

import com.netflix.hystrix.*;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import indi.uhyils.pojo.response.base.ServiceResult;
import indi.uhyils.util.LogUtil;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.AppResponse;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

import java.util.function.BiConsumer;


/**
 * dubbo熔断器配置类
 *
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年08月19日 15时43分
 */
public class DubboHystrixCommand extends HystrixCommand<Result> {


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

    private final Invoker<?> invoker;
    private final Invocation invocation;

    public DubboHystrixCommand(Invoker<?> invoker, Invocation invocation) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(invoker.getInterface().getName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(String.format("%s_%d", invocation.getMethodName(),
                        invocation.getArguments() == null ? 0 : invocation.getArguments().length)))
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
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(getThreadPoolCoreSize(invoker.getUrl()))));
        this.invoker = invoker;
        this.invocation = invocation;
    }

    /**
     * 获取线程池大小
     *
     * @param url
     * @return
     */
    private static int getThreadPoolCoreSize(URL url) {
        if (url != null) {
            int size = url.getParameter("ThreadPoolCoreSize", DEFAULT_THREADPOOL_CORE_SIZE);
            LogUtil.info("熔断器核心线程数:" + size);
            return size;
        }

        return DEFAULT_THREADPOOL_CORE_SIZE;

    }


    @Override
    protected Result getFallback() {
        ServiceResult sr = ServiceResult.buildFailedResult("您好,您请求的服务暂时不可用,请一分钟后重试!", null, null);
        try {
            return new MyAppResponse(sr);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @Override
    protected Result run() throws Exception {
        Result result = invoker.invoke(invocation);
        // 如果远程调用异常，抛出异常就会调用getFallback()方法去执行降级逻辑
        if (result.hasException()) {
            throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.COMMAND_EXCEPTION,
                    DubboHystrixCommand.class, result.getException().getMessage(),
                    result.getException(), null);
        }

        return result;
    }

    class MyAppResponse extends AppResponse {

        private Object result;

        public MyAppResponse() {
            super();
        }

        public MyAppResponse(Object result) {
            super(result);
            this.result = result;
        }

        @Override
        public Result whenCompleteWithContext(BiConsumer<Result, Throwable> fn) {
            return this;
        }
    }
}
