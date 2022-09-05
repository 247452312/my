package indi.uhyils.config;

import indi.uhyils.enums.InvokeTypeEnum;
import indi.uhyils.pojo.cqe.InvokeCommand;
import indi.uhyils.pojo.cqe.InvokeCommandBuilder;
import indi.uhyils.protocol.rpc.GatewaySdkProvider;
import indi.uhyils.protocol.rpc.impl.GatewaySdkProviderImpl;
import indi.uhyils.rpc.content.RpcHeaderContext;
import indi.uhyils.util.StringUtil;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2022年08月16日 08时58分
 */
@Component
public class GatewayConfig implements BeanPostProcessor {

    public static final String INVOKE = "invokeRpc";

    public static final String TO_STRING = "toString";

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        final Object o = BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
        if (Objects.equals(beanName, StringUtil.firstToLowerCase(GatewaySdkProviderImpl.class.getSimpleName()))) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new GatewaySdkRpcProviderHandler((GatewaySdkProvider) bean));
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    /**
     * gateway handler
     */
    private static class GatewaySdkRpcProviderHandler implements InvocationHandler {

        private final GatewaySdkProvider gatewaySdkProvider;

        private GatewaySdkRpcProviderHandler(GatewaySdkProvider gatewaySdkProvider) {
            this.gatewaySdkProvider = gatewaySdkProvider;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Objects.equals(method.getName(), TO_STRING)) {
                return "GatewaySdk";
            }
            if (Objects.equals(method.getName(), INVOKE)) {
                return gatewaySdkProvider.invokeRpc((InvokeCommand) args[0]);
            }
            final GatewaySdkProvider provider = (GatewaySdkProvider) proxy;
            InvokeCommandBuilder builder = new InvokeCommandBuilder();
            builder.addHeader(RpcHeaderContext.get());
            final List<String> argNames = Arrays.stream(method.getParameters()).map(Parameter::getName).collect(Collectors.toList());
            Map<String, Object> result = new HashMap<>(args.length);
            for (int i = 0; i < argNames.size(); i++) {
                final String argName = argNames.get(i);
                final Object arg = args[i];
                result.put(argName, arg);
            }
            builder.addArgs(result);
            builder.setType(InvokeTypeEnum.RPC.getCode());
            return provider.invokeRpc(builder.build());
        }
    }

}
